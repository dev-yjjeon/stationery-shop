package com.example.stationeryshop.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String orderNumber;

    @Column(nullable = false)
    private String customerName;

    @Column(nullable = false)
    private String customerPhone;

    @Column(nullable = false)
    private String shippingAddress;

    @Column(nullable = false)
    private Integer totalPrice;

    private LocalDateTime insDt;

    @PrePersist
    protected void onCreate() {
        this.insDt = LocalDateTime.now();
    }
}
