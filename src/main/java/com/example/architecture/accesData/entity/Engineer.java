package com.example.architecture.accesData.entity;

import javax.persistence.Entity;

/**
 * @author Timotei Molcut
 * Subclass of User. This type of user is an enginner.
 */
@Entity
public class Engineer extends User{
    public Engineer(){ }

    public Engineer(String name, String email, String password) {
        super(name, email, password);
    }
}
