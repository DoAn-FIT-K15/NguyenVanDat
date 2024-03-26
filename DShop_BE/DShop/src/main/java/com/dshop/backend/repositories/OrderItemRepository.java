package com.dshop.backend.repositories;

import com.dshop.backend.models.OrderItem;
import com.dshop.backend.models.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    OrderItem findByProductNameAndOrders(String productName, Orders checkOrders);
    long countByOrders(Orders orders);
    List<OrderItem> findByOrders(Orders orders);
}
