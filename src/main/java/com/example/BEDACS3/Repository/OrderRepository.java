package com.example.BEDACS3.Repository;

import com.example.BEDACS3.Service.model.order.OrderDTO;
import com.example.BEDACS3.Service.model.order.OrderItemDTO;

import java.util.List;

public interface OrderRepository {
    List<OrderDTO> findOrdersByUserAndStatus(String email, int status);
    List<OrderItemDTO> findOrderItemsByOrderId(int orderId);
}
