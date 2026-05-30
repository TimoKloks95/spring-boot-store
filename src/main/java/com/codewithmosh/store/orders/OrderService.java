package com.codewithmosh.store.orders;

import com.codewithmosh.store.auth.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {
    private final AuthService authService;
    private final OrderRepository orderRepository;
    private OrderMapper orderMapper;

    public List<OrderDto> getAllOrders() {
        var customerId = authService.getCurrentUserId();
        var orders = orderRepository.findOrderWithItemsByCustomerId(customerId);

        return orders.stream()
                .map(orderMapper::toDto)
                .toList();
    }

    public OrderDto getOrder(Long id) {
        var order = orderRepository
                .findOrderWithItems(id)
                .orElseThrow(OrderNotFoundException::new);

        var currentUser = authService.getCurrentUser();
        if(!order.isPlacedBy(currentUser)) {
            throw new AccessDeniedException("You do not have access to this order");
        }

        return orderMapper.toDto(order);
    }
}
