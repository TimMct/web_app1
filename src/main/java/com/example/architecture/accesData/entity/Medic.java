package com.example.architecture.accesData.entity;

import javax.persistence.Entity;

/**
 * @author Timotei Molcut
 * This is a subtype of user.
 */
@Entity
public class Medic extends User {
    public Medic() { }

    /**
     * Super constructor.
     * @param name
     * @param email
     */
    public Medic(String name, String email, String password) {
        super(name, email, password);
    }
}
