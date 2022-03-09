package com.example.db.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "positions")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "position", nullable = false)
    private String position;
}
