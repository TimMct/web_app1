package com.example.architecture.accesData.entity;

import javax.persistence.*;

/**
 * @author Timotei Molcut
 * This is used to update the notification string of an user is case a like has been added to a photo.
 * It basically implements the observer pattern.
 */
@Entity
public class LikeObserver {

    /**
     * The primary key to store this entity in data base.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    public Integer id;

    /**
     * This is the user which will be updated (the notification string attribute will be changed).
     */
    @OneToOne(cascade = CascadeType.REMOVE)
    private User user;

    /**
     * Basic constructor.
     */
    public LikeObserver() { }

    public void setUser(User user){
        this.user = user;
    }

    /**
     * This method updates the notification saying that the liker liked the picture on the position mentioned.
     * @param liker
     * @param picturePos
     */
    public void update(User liker, int picturePos){
        this.user.changeLikeNotification(liker + " liked your picture #"+picturePos);
    }


}
