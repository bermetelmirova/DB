package com.example.db.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "employees")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    @ManyToOne
    @JoinColumn(name = "position_id")
    private Position position;

    @Column(name = "payment")
    private Float payment;

    @Column(name = "address")
    private String address;

    @Column(name = "phone_number")
    private String phoneNumber;

}
