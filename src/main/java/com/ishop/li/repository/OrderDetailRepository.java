package com.ishop.li.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ishop.li.model.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
}
