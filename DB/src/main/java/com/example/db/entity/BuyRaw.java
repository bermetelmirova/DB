package com.example.db.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "buy_raws")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class BuyRaw {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "raw_id")
    private Raw raw;

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
