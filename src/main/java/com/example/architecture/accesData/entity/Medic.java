package com.example.architecture.accesData.entity;

import javax.persistence.Entity;

@Entity
public class Medic extends User {
    public Medic() { }
    public Medic(String name, String email) {
        super(name, email);
    }
}
