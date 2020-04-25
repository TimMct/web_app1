package com.example.accessingdatamysql.architecture;

/**
 * This class is just for behaviour tests.
 */
public class Main {


    public static void main(String[] args) {
        UserFactory factory = new UserFactory();
        User first = factory.getUserByType(1, "firstUser", "first@email.com", "engineer");
        User second = factory.getUserByType(2, "secondUser", "second@email.com", "teacher");

        if(first.equals(null) || second.equals(null)){
            System.out.println("Something went wrong");
            return;
        }
        first.addPicture(new Picture(1, first, "emi.jpg"));
        first.addPicture(new Picture(2, first, "eminescu.jpg"));
        second.addPicture(new Picture(3, second, "vero.jpg"));

        System.out.println(second + " notifications: "+second.getLikeNotification());
        System.out.println();

        first.likePicture(second, 0);
        System.out.println(second + " notifications: "+second.getLikeNotification());
        System.out.println(second.getPictures().get(0));
        System.out.println();

        first.likePicture(second, 0);
        System.out.println(second + " notifications: "+second.getLikeNotification());
        System.out.println(second.getPictures().get(0));
        System.out.println();

        first.unlikePicture(second, 0);
        System.out.println(second + " notifications: "+second.getLikeNotification());
        System.out.println(second.getPictures().get(0));
        System.out.println();

        first.unlikePicture(second, 0);
        System.out.println(second + " notifications: "+second.getLikeNotification());
        System.out.println(second.getPictures().get(0));
        System.out.println();


        System.out.println("Pictures of "+first.getName());
        for(Picture p : first.getPictures()){
            System.out.println(p);
        }

        System.out.println("Pictures of "+second.getName());
        for(Picture p : second.getPictures())
            System.out.println(p);

        System.out.println();
        first.likePicture(first, 1);
        System.out.println(first + " notifications: "+first.getLikeNotification());
        System.out.println(first.getPictures().get(1));
        System.out.println();

        first.unlikePicture(second, 1);
        System.out.println(second + " notifications: "+second.getLikeNotification());
        //System.out.println(second.getPictures().get(1));
        System.out.println();

    }
}
