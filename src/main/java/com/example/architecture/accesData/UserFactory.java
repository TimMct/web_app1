package com.example.architecture.accesData;

import com.example.architecture.accesData.entity.Engineer;
import com.example.architecture.accesData.entity.Medic;
import com.example.architecture.accesData.entity.Teacher;
import com.example.architecture.accesData.entity.User;

public class UserFactory {

    public User getUserByType(String name, String email, String type){
        if(type == null)
            return null;
        if(type.equalsIgnoreCase("ENGINEER"))
            return new Engineer(name, email);
        if(type.equalsIgnoreCase("MEDIC"))
            return new Medic(name, email);
        if(type.equalsIgnoreCase("TEACHER"))
            return new Teacher(name, email);
        return null;
    }

}
