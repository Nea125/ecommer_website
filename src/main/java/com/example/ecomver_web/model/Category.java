package com.example.ecomver_web.model;

import jakarta.persistence.*;



@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    private String name;

    // Constructors, getters, setters, and other methods
}
