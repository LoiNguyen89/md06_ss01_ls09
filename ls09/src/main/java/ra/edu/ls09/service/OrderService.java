package ra.edu.ls09.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ra.edu.ls09.model.Order;
import ra.edu.ls09.repo.OrderRepository;

import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public Page<Order> getOrdersByStatus(String status, Pageable pageable) {
        if (status != null && !status.isEmpty()) {
            return orderRepository.findByStatus(status, pageable);
        }
        return orderRepository.findAll(pageable);
    }

    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    public Optional<Order> updateOrder(Long id, Order updatedOrder) {
        return orderRepository.findById(id).map(existing -> {
            existing.setProductName(updatedOrder.getProductName());
            existing.setQuantity(updatedOrder.getQuantity());
            existing.setTotalPrice(updatedOrder.getTotalPrice());
            existing.setStatus(updatedOrder.getStatus());
            return orderRepository.save(existing);
        });
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
