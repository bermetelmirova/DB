package com.example.db.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "ready_products")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReadyProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product", nullable = false)
    private String product;

    @ManyToOne
    @JoinColumn(name = "unit_id", nullable = false)
    private Unit unit;

    @Column(name = "price", nullable = false)
    private Float price;

    @Column(name = "amount", nullable = false)
    private Integer amount;
}
