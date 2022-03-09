package com.example.db.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "ingredients")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ReadyProduct readyProduct;

    @ManyToOne
    @JoinColumn(name = "raw_id")
    private Raw raw;

    @Column(name = "amount")
    private Integer amount;
}
