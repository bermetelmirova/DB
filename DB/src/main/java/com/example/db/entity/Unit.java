package com.example.db.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "units")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Unit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "value_name")
    private String valueName;
}
