package com.fiap.techfood.infrastructure.controller;

import com.fiap.techfood.application.dto.request.OrderRequestDTO;
import com.fiap.techfood.application.dto.request.SearchOrdersRequestDTO;
import com.fiap.techfood.application.interfaces.usecases.OrderUseCases;
import com.fiap.techfood.domain.order.Order;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@Tag(name = "Pedidos")
public class OrderController {

    private final OrderUseCases useCases;

    public OrderController(OrderUseCases useCases) {
        this.useCases = useCases;
    }

    @PostMapping
    @Transactional
    @Operation(summary = "Cria um pedido")
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequestDTO request) {
        Order order = useCases.createOrder(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }

    @GetMapping("/status")
    @Operation(summary = "Lista todos os pedidos a partir de um status e um intervalo de tempo")
    ResponseEntity<List<Order>> findOrdersByStatusAndTimeInterval(@Valid SearchOrdersRequestDTO searchOrdersRequestDTO) {
        return ResponseEntity.ok(useCases.findOrdersByStatusAndTimeInterval(searchOrdersRequestDTO));
    }

    @GetMapping("/{orderId}")
    @Operation(summary = "Retorna um pedido pelo orderId")
    ResponseEntity<Order> findById(@PathVariable Long orderId) {
        return ResponseEntity.ok(useCases.findById(orderId));
    }
}
