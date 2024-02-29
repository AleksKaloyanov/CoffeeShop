package org.example.coffeeshop.repository;

import org.example.coffeeshop.model.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity,Long> {

    List<OrderEntity> findAllOrderByPriceDesc();
}
