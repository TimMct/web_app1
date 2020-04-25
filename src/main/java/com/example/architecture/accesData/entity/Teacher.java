package com.example.architecture.accesData.entity;

import javax.persistence.Entity;

@Entity
public class Teacher extends User{
    public Teacher() { }
    public Teacher(String name, String email) {
        super(name, email);
    }
}
