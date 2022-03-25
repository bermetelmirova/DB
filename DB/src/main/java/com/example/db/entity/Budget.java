package com.example.db.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "budget")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Budget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "budget_sum")
    private Float budgetSum;

    @Column(name = "bonus")
    private Float bonus;

    @Column(name = "percent_amount")
    private Float percent;
}
