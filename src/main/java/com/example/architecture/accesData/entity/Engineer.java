package com.example.architecture.accesData.entity;

import javax.persistence.Entity;

@Entity
public class Engineer extends User{
    public Engineer(){ }
    public Engineer(String name, String email) { super(name, email); }
}
