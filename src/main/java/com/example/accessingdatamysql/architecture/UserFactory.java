package com.example.accessingdatamysql.architecture;

public class UserFactory {

    public User getUserByType(Integer userId, String name, String email, String type){
        if(type == null)
            return null;
        if(type.equalsIgnoreCase("engineer"))
            return new Engineer(userId, name, email);
        if(type.equalsIgnoreCase("medic"))
            return new Medic(userId, name, email);
        if(type.equalsIgnoreCase("teacher"))
            return new Teacher(userId, name, email);
        return null;
    }

}
