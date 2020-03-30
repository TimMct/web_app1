package com.example.accessingdatamysql.arch;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Timotei Molcut
 */
@Entity
public class User {
    /**
     * primary key for user table
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer userId;
    /**
     * name for the user
     */
    @Column
    private String name;
    /**
     * email for the user
     */
    @Column
    private String email;

    /**
     * relation OneToMany with the table picture
     * a user can have a list of pictures
     */
    @OneToMany(mappedBy = "owner")
    private List<Picture> pictures = new ArrayList<Picture>();


    //private List<User> friends;

    //empty constructor


    public User() {
    }

    /**
     * constructor
     * @param userId
     * @param name
     * @param email
     */
    public User(Integer userId, String name, String email){
        this.userId = userId;
        this.name = name;
        this.email = email;
    }


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer id) {
        this.userId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyAllObservers();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        notifyAllObservers();
    }



    public List<Picture> getPictures(){
        return pictures;
    }


    /**
     * for observer pattern
     */
    public void notifyAllObservers(){
        for(Picture pic : this.getPictures()){
            pic.update();
        }
    }




    /**
     * method needed to increment the # of likes of a picture, owned by a user
     * @param u the owner
     * @param p the picture to be liked
     */
    public void likePicture(User u, Picture p){
        for(Picture pic: u.getPictures()){
            if(pic.equals(p)){
                p.setNrOfLikes(p.getNrOfLikes()+1);
                return;
            }
        }
    }

    /**
     * method needed to decrement the # of likes of a picture, owned by a user
     * @param u
     * @param p
     */
    public void unlikePicture(User u, Picture p){
        for(Picture pic: u.getPictures()){
            if(pic.equals(p)){
                p.setNrOfLikes(p.getNrOfLikes()-1);
                return;
            }
        }
    }

    /**
     * incomplete method
     * @param u
     */
    public void sendFR(User u){

    }

    /**
     * overriding the method toString
     * @return the name and the email of the user
     */
    @Override
    public String toString(){
        return this.name+"  "+this.email;
    }

}