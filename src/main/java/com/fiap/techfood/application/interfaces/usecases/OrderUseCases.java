package com.fiap.techfood.application.interfaces.usecases;

import com.fiap.techfood.application.dto.request.OrderRequestDTO;
import com.fiap.techfood.application.dto.request.SearchOrdersRequestDTO;
import com.fiap.techfood.domain.order.Order;
import com.fiap.techfood.domain.order.OrderStatus;

import java.util.List;

public interface OrderUseCases {
    Order createOrder(OrderRequestDTO requestDTO);

    Order updateOrderStatus(Long orderNumber, OrderStatus status);

    List<Order> findOrdersByStatusAndTimeInterval(SearchOrdersRequestDTO searchOrdersRequestDTO);

    List<Order> findNotCompletedOrders();
}
