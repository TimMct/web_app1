package com.example.accessingdatamysql.arch;

public class ObserverDemo {

    public static void main(String[] args) {
        User first = new User(1, "first", "first@email.com");
        new Picture(1, first, "fff.jpg", 0);
        new Picture(2, first, "fuf.jpg", 0);

        System.out.println("Change name");
        first.setName("first_ok");
        System.out.println("Change email");
        first.setEmail("first_ok@yahoo.com");
    }

}
