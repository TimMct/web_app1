package com.example.accessingdatamysql.architecture;

/**
 * This class is just for behaviour tests.
 */
public class Main {


    public static void main(String[] args) {

        UserFactory factory = new UserFactory();
        User first = factory.getUserType(1, "first", "first@email.com", "engineer");
        User second = factory.getUserType(2, "second", "second@email.com", "teacher");

        if(first.equals(null) || second.equals(null)){
            System.out.println("Something went wrong");
            return;
        }

        first.addPicture(new Picture(1, first, "emi.jpg"));
        second.addPicture(new Picture(2, second, "vero.jpg"));

        first.attachLikeObserver();
        second.attachLikeObserver();
        System.out.println("Like notifications for "+second.getClass().getSimpleName()+" \""+second.getName() + "\" are: "+second.getLikeNotification());

        first.likePicture(second, 0);

        System.out.println("Like notifications for "+second.getClass().getSimpleName()+" \""+second.getName() + "\" are: "+second.getLikeNotification());

        System.out.println("The pic "+second.getPictures().get(0).getName()+" has "+second.getPictures().get(0).getNrOfLikes()+" like/likes.");
    }

}
