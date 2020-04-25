package com.example.architecture;

import com.example.architecture.accesData.entity.Picture;
import com.example.architecture.accesData.entity.User;
import com.example.architecture.accesData.UserFactory;

import java.util.HashSet;
import java.util.Set;

/**
 * This class is just for behaviour tests.
 */
public class Behaviour {


    public static void main(String[] args) {
        UserFactory factory = new UserFactory();
        User first = factory.getUserByType("firstUser", "first@email.com", "engineer");
        User second = factory.getUserByType("secondUser", "second@email.com", "teacher");

        if(first.equals(null) || second.equals(null)){
            System.out.println("Something went wrong");
            return;
        }
        first.addPicture(new Picture( first, "emi.jpg"));
        first.addPicture(new Picture( first, "eminescu.jpg"));
        second.addPicture(new Picture( second, "vero.jpg"));

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



        Set<User> temp = new HashSet<User>();


        temp.add(first);
        temp.add(first);
        //temp.add(second);
        temp.remove(first);



        for(User s : temp){
            System.out.println(s.getName());
        }

        System.out.println("___________________________________________________________________________________");

        System.out.print("\nFriends of "+first.getName()+" are: ");
        for(User friend : first.getFriends())
            System.out.print(friend.getName());

        System.out.print("\nFriends of "+second.getName()+" are: ");
        for(User friend : second.getFriends())
            System.out.print(friend.getName());


        second.addFriend(first);

        System.out.print("\nFriends of "+first.getName()+" are: ");
        for(User friend : first.getFriends())
            System.out.print(friend.getName());

        System.out.print("\nFriends of "+second.getName()+" are: ");
        for(User friend : second.getFriends())
            System.out.print(friend.getName());

    }
}
