package com.ishop.li.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ishop.li.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
