package com.example.accessingdatamysql.architecture;

public class LikeObserver {

    private User user;

    public LikeObserver(User user){
        this.user = user;
    }

    public void update(User user, int picturePos){
        this.user.changeLikeNotification("The user " + user.getName() + " liked your picture #"+picturePos);
    }


}
