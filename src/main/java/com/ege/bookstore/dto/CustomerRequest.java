package com.ege.bookstore.dto;

import jakarta.validation.constraints.NotBlank;

public class CustomerRequest {
    @NotBlank(message = "Username is required")
    private String username;
    @NotBlank(message = "Name is required")
    private String name;
    @NotBlank(message = "Surname is required")
    private String surname;

    public CustomerRequest(){}

    public String getUsername(){
        return username;
    }
    public String getName(){
        return name;
    }
    public String getSurname(){
        return surname;
    }
    public void setUsername(String username){
        this.username=username;
    }
    public void setName(String name){
        this.name=name;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
}
