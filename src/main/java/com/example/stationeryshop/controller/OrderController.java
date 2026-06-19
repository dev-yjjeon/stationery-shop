package com.example.stationeryshop.controller;

import com.example.stationeryshop.dto.OrderRequestDto;
import com.example.stationeryshop.entity.CartItem;
import com.example.stationeryshop.entity.Order;
import com.example.stationeryshop.entity.OrderItem;
import com.example.stationeryshop.service.CartService;
import com.example.stationeryshop.service.OrderService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final CartService cartService;

    @ModelAttribute("cartCount")
    public int getCartCount(HttpSession session) {
        return cartService.getCartItems(session.getId()).stream()
                .mapToInt(CartItem::getQuantity)
                .sum();
    }

    @GetMapping("/order")
    public String orderForm(HttpSession session, Model model) {
        List<CartItem> cartItems = cartService.getCartItems(session.getId());
        if (cartItems.isEmpty()) {
            return "redirect:/cart";
        }

        int totalPrice = cartItems.stream()
                .mapToInt(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();

        model.addAttribute("cartItems", cartItems);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("orderRequestDto", new OrderRequestDto());
        return "order";
    }

    @PostMapping("/order")
    public String processOrder(@Valid @ModelAttribute("orderRequestDto") OrderRequestDto dto,
                               BindingResult bindingResult,
                               HttpSession session,
                               Model model) {
        List<CartItem> cartItems = cartService.getCartItems(session.getId());
        int totalPrice = cartItems.stream()
                .mapToInt(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();

        if (bindingResult.hasErrors()) {
            model.addAttribute("cartItems", cartItems);
            model.addAttribute("totalPrice", totalPrice);
            return "order";
        }

        try {
            Order order = orderService.createOrder(session.getId(), dto);
            return "redirect:/order/complete/" + order.getId();
        } catch (RuntimeException e) {
            model.addAttribute("cartItems", cartItems);
            model.addAttribute("totalPrice", totalPrice);
            model.addAttribute("errorMessage", e.getMessage());
            return "order";
        }
    }

    @GetMapping("/order/complete/{orderId}")
    public String orderComplete(@PathVariable("orderId") Long orderId, Model model) {
        Order order = orderService.getOrderById(orderId);
        List<OrderItem> orderItems = orderService.getOrderItemsByOrderId(orderId);

        model.addAttribute("order", order);
        model.addAttribute("orderItems", orderItems);
        return "order-complete";
    }
}
