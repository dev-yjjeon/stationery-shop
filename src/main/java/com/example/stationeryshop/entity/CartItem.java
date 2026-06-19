package com.example.stationeryshop.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "cart_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String sessionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(nullable = false)
    private Integer quantity;

    private LocalDateTime insDt;

    private LocalDateTime updDt;

    @PrePersist
    protected void onCreate() {
        this.insDt = LocalDateTime.now();
        this.updDt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updDt = LocalDateTime.now();
    }
}
