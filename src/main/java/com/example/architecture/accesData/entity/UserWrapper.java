package com.example.architecture.accesData.entity;

public class UserWrapper {
    private String name;
    private String email;
    private String type;
    private String password;


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "UserWrapper{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
