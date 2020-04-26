package com.example.architecture.accesData.entity;

import javax.persistence.Entity;

/**
 * @author TimoteiMolcut
 * This is another type of user.
 */
@Entity
public class Teacher extends User{
    public Teacher() { }

    /**
     * Super constructor.
     * @param name
     * @param email
     */
    public Teacher(String name, String email) {
        super(name, email);
    }
}
