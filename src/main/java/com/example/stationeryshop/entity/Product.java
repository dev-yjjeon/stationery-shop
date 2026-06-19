package com.example.stationeryshop.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer price;

    private String description;

    private String imageUrl;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category;

    @Column(nullable = false)
    private Integer stock;

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
