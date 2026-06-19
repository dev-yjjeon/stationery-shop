package com.example.stationeryshop.service;

import com.example.stationeryshop.dto.OrderRequestDto;
import com.example.stationeryshop.entity.CartItem;
import com.example.stationeryshop.entity.Order;
import com.example.stationeryshop.entity.OrderItem;
import com.example.stationeryshop.entity.Product;
import com.example.stationeryshop.repository.CartItemRepository;
import com.example.stationeryshop.repository.OrderItemRepository;
import com.example.stationeryshop.repository.OrderRepository;
import com.example.stationeryshop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final CartService cartService;

    public Order createOrder(String sessionId, OrderRequestDto orderRequestDto) {
        List<CartItem> cartItems = cartItemRepository.findBySessionId(sessionId);
        if (cartItems.isEmpty()) {
            throw new IllegalArgumentException("장바구니가 비어 있습니다.");
        }

        int totalPrice = 0;
        for (CartItem item : cartItems) {
            Product product = item.getProduct();
            if (product.getStock() < item.getQuantity()) {
                throw new RuntimeException("상품 [" + product.getName() + "]의 재고가 부족합니다. (현재 재고: " + product.getStock() + ")");
            }
            totalPrice += product.getPrice() * item.getQuantity();
        }

        String orderNumber = "ORD-" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) + "-" + UUID.randomUUID().toString().substring(0, 4).toUpperCase();

        Order order = Order.builder()
                .orderNumber(orderNumber)
                .customerName(orderRequestDto.getCustomerName())
                .customerPhone(orderRequestDto.getCustomerPhone())
                .shippingAddress(orderRequestDto.getShippingAddress())
                .totalPrice(totalPrice)
                .build();
        Order savedOrder = orderRepository.save(order);

        for (CartItem item : cartItems) {
            Product product = item.getProduct();
            product.setStock(product.getStock() - item.getQuantity());
            productRepository.save(product);

            OrderItem orderItem = OrderItem.builder()
                    .order(savedOrder)
                    .product(product)
                    .quantity(item.getQuantity())
                    .price(product.getPrice())
                    .build();
            orderItemRepository.save(orderItem);
        }

        cartService.clearCart(sessionId);

        return savedOrder;
    }

    @Transactional(readOnly = true)
    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 주문이 존재하지 않습니다. ID: " + id));
    }

    @Transactional(readOnly = true)
    public List<OrderItem> getOrderItemsByOrderId(Long orderId) {
        return orderItemRepository.findByOrderId(orderId);
    }
}
