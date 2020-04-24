package com.example.accessingdatamysql.architecture;

public class Main {

    public static void main(String[] args) {
        User first = new User(1, "first", "first@email.com");
        User second = new User(2, "second", "second@email.com");
        first.addPicture(new Picture(1, first, "emi.jpg"));
        second.addPicture(new Picture(2, second, "vero.jpg"));

        second.attachLikeObserver();
        System.out.println("Like notifications for user "+second.getName() + " are: "+second.getLikeNotification());

        first.likePicture(second, 0);

        System.out.println("Like notifications for user "+second.getName() + " are: "+second.getLikeNotification());

        System.out.println("The pic "+second.getPictures().get(0).getName()+" has "+second.getPictures().get(0).getNrOfLikes()+" like/likes.");
    }

}
