package com.codewithmosh.store.orders;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @EntityGraph(attributePaths = "items.product")
    @Query("SELECT o from Order o WHERE o.customer.id = :customerId")
    List<Order> findOrderWithItemsByCustomerId(@Param("customerId") Long customerId);

    @EntityGraph(attributePaths = "items.product")
    @Query("SELECT o from Order o WHERE o.id = :id")
    Optional<Order> findOrderWithItems(@Param("id") Long id);
}