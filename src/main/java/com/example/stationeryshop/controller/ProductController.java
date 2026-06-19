package com.example.stationeryshop.controller;

import com.example.stationeryshop.entity.Product;
import com.example.stationeryshop.service.CartService;
import com.example.stationeryshop.service.ProductService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final CartService cartService;

    @ModelAttribute("cartCount")
    public int getCartCount(HttpSession session) {
        return cartService.getCartItems(session.getId()).stream()
                .mapToInt(item -> item.getQuantity())
                .sum();
    }

    @GetMapping("/")
    public String index(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "index";
    }

    @GetMapping("/product/{id}")
    public String detail(@PathVariable("id") Long id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "detail";
    }
}
