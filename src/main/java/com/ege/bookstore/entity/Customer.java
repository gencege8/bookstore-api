package com.ege.bookstore.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name="customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Username is required")
    @Column(nullable = false, unique = true)
    private String username;
    @NotBlank(message = "Name is required")
    @Column(nullable = false)
    private String name;
    @NotBlank(message = "Surname is required")
    @Column(nullable = false)
    private String surname;
    public Customer(){}
    public Customer(String username, String name, String surname){
        this.username=username;
        this.name=name;
        this.surname=surname;
    }
    public String getName(){
        return this.name;
    }
    public String getUsername(){
        return this.username;
    }
    public String getSurname(){
        return this.surname;
    }
    public Long getId(){
        return this.id;
    }
    public void setName(String name){
        this.name=name;
    }
    public void setSurname(String surname){
        this.surname=surname;
    }
    public void setUsername(String username){
        this.username=username;
    }
}
