package com.example.db.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "raws")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Raw {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "raw_name", nullable = false)
    private String rawName;

    @ManyToOne
    @JoinColumn(name = "unit_id", nullable = false)
    private Unit unit;

    @Column(name = "price", nullable = false)
    private Float price;

    @Column(name = "amount", nullable = false)
    private Integer amount;
}
