package com.example.architecture.accesData.entity;

import com.example.architecture.accesData.entity.User;

import javax.persistence.*;

@Entity
public class LikeObserver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    public Integer id;

    @OneToOne
    private User user;

    public LikeObserver() { }

    public LikeObserver(User user){
        this.user = user;
    }

    public void setUser(User user){
        this.user = user;
    }

    public void update(User user, int picturePos){
        this.user.changeLikeNotification(user + " liked your picture #"+picturePos);
    }


}
