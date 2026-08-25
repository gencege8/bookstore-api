package com.ege.bookstore.dto;

public class CustomerResponse {
    private final Long id;
    private final String username;

    private final String name;

    private final String surname;

    public CustomerResponse(Long id, String username, String name, String surname){
        this.id = id;
        this.username=username;
        this.name=name;
        this.surname = surname;
    }

    public Long getId(){
        return id;
    }
    public String getUsername(){
       return username;
    }
    public String getName(){
        return name;
    }
    public String getSurname(){
        return surname;
    }
}
