package com.example.db.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "sell_products")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SellProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ready_product")
    private ReadyProduct readyProduct;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "sum")
    private Float sum;

    @Column(name = "date")
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
}
