package com.example.architecture.accesData.entity;

/**
 * @author TimoteiMolcut
 * This class implements the factory design pattern.
 */
public class UserFactory {

    /**
     * A new user is created with the name and string provided if type is one of these 3: engineer, medic or teacher.
     * Otherwise a null user will be returned. Hence, there are three types of user and they are subclasses of User.
     * @param name
     * @param email
     * @param type
     * @return
     */
    public User getUserByType(String name, String email, String password, String type){
        if(type == null)
            return null;
        if(type.equalsIgnoreCase("ENGINEER"))
            return new Engineer(name, email, password);
        if(type.equalsIgnoreCase("MEDIC"))
            return new Medic(name, email, password);
        if(type.equalsIgnoreCase("TEACHER"))
            return new Teacher(name, email, password);
        return null;
    }

}
