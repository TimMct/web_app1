package com.example.accessingdatamysql.architecture;

public class ObserverDemo {

    public static void main(String[] args) {
        User first = new User(1, "first", "first@email.com");
        new Picture(1, first, "fff.jpg");
        new Picture(2, first, "fuf.jpg");

        System.out.println("Change name");
        first.setName("first_ok");
        System.out.println("Change email");
        first.setEmail("first_ok@yahoo.com");
    }

}
