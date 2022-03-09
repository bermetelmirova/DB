package com.example.db.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "productions")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Production {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ReadyProduct readyProduct;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "date")
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
}
