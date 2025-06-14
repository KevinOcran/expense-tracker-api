package com.expensetracker.backend.entity;


import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name ="category")
@Data
@Builder
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Expense> expenses;

}
