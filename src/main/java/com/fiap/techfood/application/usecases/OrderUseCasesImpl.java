package com.fiap.techfood.application.usecases;

import com.fiap.techfood.application.dto.request.OrderRequestDTO;
import com.fiap.techfood.application.dto.request.SearchOrdersRequestDTO;
import com.fiap.techfood.application.dto.response.OrderPaymentStatusDTO;
import com.fiap.techfood.application.interfaces.gateways.CustomerRepository;
import com.fiap.techfood.application.interfaces.gateways.OrderMessageSender;
import com.fiap.techfood.application.interfaces.gateways.OrderRepository;
import com.fiap.techfood.application.interfaces.gateways.ProductRepository;
import com.fiap.techfood.application.interfaces.usecases.OrderUseCases;
import com.fiap.techfood.domain.commons.HttpStatusCodes;
import com.fiap.techfood.domain.commons.exception.BusinessException;
import com.fiap.techfood.domain.customer.Customer;
import com.fiap.techfood.domain.order.CreatedOrder;
import com.fiap.techfood.domain.order.Order;
import com.fiap.techfood.domain.order.OrderItem;
import com.fiap.techfood.domain.order.OrderPaymentStatus;
import com.fiap.techfood.domain.order.OrderStatus;
import com.fiap.techfood.domain.products.Product;
import org.apache.commons.lang3.ObjectUtils;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class OrderUseCasesImpl implements OrderUseCases {
    private final OrderRepository repo;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;

    private final OrderMessageSender orderMessageSender;

    public OrderUseCasesImpl(final OrderRepository orderRepository, final ProductRepository productRepository,
                             final CustomerRepository customerRepository, final OrderMessageSender orderMessageSender) {
        this.repo = orderRepository;
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
        this.orderMessageSender = orderMessageSender;
    }

    @Override
    public Order createOrder(OrderRequestDTO requestDTO) {
        Order order = Order.fromOrderRequestDTO(requestDTO);

        if (requestDTO.getCustomerId() != null) {
            Customer customer = customerRepository.findById(requestDTO.getCustomerId())
                    .orElseThrow(() -> new BusinessException("Customer provided not found", HttpStatusCodes.BAD_REQUEST));
            order.setCustomer(customer);
        }

        order.setStatus(OrderStatus.CREATED);
        order.setCreatedDateTime(OffsetDateTime.now());

        List<Product> products = getAndValidateProducts(getProductIds(requestDTO.getItems()));
        List<OrderItem> orderItems = buildOrderItems(requestDTO.getItems(), products);
        order.setItems(orderItems);
        order.setTotalValue(calculateOrderTotalValue(orderItems));

        Order savedOrder = repo.save(order);

        orderMessageSender.publish(CreatedOrder.builder()
                        .number(savedOrder.getNumber())
                        .createdDateTime(savedOrder.getCreatedDateTime())
                        .customerId(savedOrder.getCustomer().getId())
                .build());

        return savedOrder;
    }

    private BigDecimal calculateOrderTotalValue(List<OrderItem> orderItems) {
        return orderItems.stream()
                .map(OrderItem::calculateItemTotalValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private List<OrderItem> buildOrderItems(List<OrderRequestDTO.OrderItemRequestDTO> requestItems, List<Product> products) {
        List<OrderItem> orderItems = new ArrayList<>();
        for (OrderRequestDTO.OrderItemRequestDTO item : requestItems) {
            Product currentProduct = products.stream().filter(product -> product.getId().equals(item.getProductId())).findFirst().orElseThrow();
            orderItems.add(OrderItem.builder()
                    .quantity(item.getQuantity())
                    .unitPrice(currentProduct.getPrice())
                    .product(currentProduct)
                    .build());
        }
        return orderItems;
    }

    private List<Product> getAndValidateProducts(List<Long> productIds) {
        List<Product> products = productRepository.findAllByIdIn(productIds);
        if (products.size() != productIds.size()) {
            throw new BusinessException("Produto(s) não encontrado", HttpStatusCodes.NOT_FOUND);
        }
        return products;
    }

    private static List<Long> getProductIds(List<OrderRequestDTO.OrderItemRequestDTO> items) {
        return items.stream().map(OrderRequestDTO.OrderItemRequestDTO::getProductId)
                .toList();
    }

    @Override
    public Order updateOrderStatus(Long orderNumber, OrderStatus status) {
        Order order = repo.findById(orderNumber).orElseThrow(() -> new BusinessException("ID do pedido não encontrado", HttpStatusCodes.NOT_FOUND));
        order.setStatus(status);
        repo.updateOrderStatus(order);
        return order;
    }

    @Override
    public List<Order> findOrdersByStatusAndTimeInterval(SearchOrdersRequestDTO searchOrdersRequestDTO) {
        var validStartDateTime = ObjectUtils.firstNonNull(searchOrdersRequestDTO.getStartDatetime(), OffsetDateTime.now().minusDays(1));
        var validEndDateTime = ObjectUtils.firstNonNull(searchOrdersRequestDTO.getEndDatetime(), OffsetDateTime.now());

        if (validEndDateTime.isBefore(validStartDateTime)) {
            throw new BusinessException("A data final deve ser posterior a data inicial!", HttpStatusCodes.BAD_REQUEST);
        }

        return repo.findOrdersByStatusAndTimeInterval(searchOrdersRequestDTO.getStatus(), validStartDateTime, validEndDateTime);
    }

    @Override
    public List<Order> findNotCompletedOrders() {
        return repo.findAllNotCompleted().stream()
                .filter(order -> order.getReceivedDateTime() != null)
                .sorted(Comparator.comparing((Order order) -> order.getStatus().getDisplayPriority())
                        .thenComparing(Order::getReceivedDateTime))
                .toList();
    }

    @Override
    public OrderPaymentStatusDTO getOrderPaymentStatus(Long orderNumber) {
        Order order = repo.findById(orderNumber).orElseThrow(() -> new BusinessException("Status não alterado, ID não encontrado!", HttpStatusCodes.NOT_FOUND));

        if(order.getStatus() == OrderStatus.REJECTED) {
            return OrderPaymentStatusDTO.builder().status(OrderPaymentStatus.REJECTED).build();
        } else if (order.getStatus() != OrderStatus.CREATED) {
            return OrderPaymentStatusDTO.builder().status(OrderPaymentStatus.APPROVED).build();
        }

        return OrderPaymentStatusDTO.builder().status(OrderPaymentStatus.PENDING).build();
    }
}
