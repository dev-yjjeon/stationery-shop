package com.example.stationeryshop.service;

import com.example.stationeryshop.dto.CartRequestDto;
import com.example.stationeryshop.entity.CartItem;
import com.example.stationeryshop.entity.Product;
import com.example.stationeryshop.repository.CartItemRepository;
import com.example.stationeryshop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CartService {

    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;

    public List<CartItem> getCartItems(String sessionId) {
        return cartItemRepository.findBySessionId(sessionId);
    }

    @Transactional
    public void addToCart(String sessionId, CartRequestDto dto) {
        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상품입니다."));

        Optional<CartItem> existingItemOpt = cartItemRepository.findBySessionIdAndProductId(sessionId, dto.getProductId());

        if (existingItemOpt.isPresent()) {
            CartItem cartItem = existingItemOpt.get();
            cartItem.setQuantity(cartItem.getQuantity() + dto.getQuantity());
            cartItemRepository.save(cartItem);
        } else {
            CartItem cartItem = CartItem.builder()
                    .sessionId(sessionId)
                    .product(product)
                    .quantity(dto.getQuantity())
                    .build();
            cartItemRepository.save(cartItem);
        }
    }

    @Transactional
    public void updateQuantity(String sessionId, Long cartItemId, int quantity) {
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new IllegalArgumentException("장바구니 아이템이 존재하지 않습니다."));

        if (!cartItem.getSessionId().equals(sessionId)) {
            throw new SecurityException("잘못된 접근입니다.");
        }

        if (quantity <= 0) {
            throw new IllegalArgumentException("수량은 1개 이상이어야 합니다.");
        }

        cartItem.setQuantity(quantity);
        cartItemRepository.save(cartItem);
    }

    @Transactional
    public void removeCartItem(String sessionId, Long cartItemId) {
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new IllegalArgumentException("장바구니 아이템이 존재하지 않습니다."));

        if (!cartItem.getSessionId().equals(sessionId)) {
            throw new SecurityException("잘못된 접근입니다.");
        }

        cartItemRepository.delete(cartItem);
    }

    @Transactional
    public void clearCart(String sessionId) {
        cartItemRepository.deleteBySessionId(sessionId);
    }
}
