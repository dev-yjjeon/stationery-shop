package com.example.stationeryshop.controller;

import com.example.stationeryshop.dto.CartRequestDto;
import com.example.stationeryshop.entity.CartItem;
import com.example.stationeryshop.service.CartService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @ModelAttribute("cartCount")
    public int getCartCount(HttpSession session) {
        return cartService.getCartItems(session.getId()).stream()
                .mapToInt(CartItem::getQuantity)
                .sum();
    }

    @GetMapping("/cart")
    public String cart(HttpSession session, Model model) {
        List<CartItem> cartItems = cartService.getCartItems(session.getId());
        int totalPrice = cartItems.stream()
                .mapToInt(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("totalPrice", totalPrice);
        return "cart";
    }

    @PostMapping("/api/cart")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> addToCart(@RequestBody @Valid CartRequestDto dto, HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        try {
            cartService.addToCart(session.getId(), dto);
            response.put("success", true);
            response.put("message", "장바구니에 상품을 성공적으로 담았습니다.");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/api/cart/update")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> updateQuantity(@RequestBody CartUpdateDto dto, HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        try {
            cartService.updateQuantity(session.getId(), dto.getCartItemId(), dto.getQuantity());
            response.put("success", true);
            response.put("message", "수량이 변경되었습니다.");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/api/cart/{id}")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> removeCartItem(@PathVariable("id") Long cartItemId, HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        try {
            cartService.removeCartItem(session.getId(), cartItemId);
            response.put("success", true);
            response.put("message", "장바구니에서 상품을 삭제했습니다.");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @Getter
    @Setter
    public static class CartUpdateDto {
        private Long cartItemId;
        private Integer quantity;
    }
}
