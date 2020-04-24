package com.example.accessingdatamysql.architecture;

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

    @Transient
    private LikeObserver likeObserver;




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

    public void addPicture(Picture picture){
        this.pictures.add(picture);
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

    @Transient
    private String likeNotification;

    public String getLikeNotification(){
        return this.likeNotification;
    }

    public void changeLikeNotification(String newNotification){
        this.likeNotification = newNotification;
    }

    public void attachLikeObserver(){
        this.likeObserver = new LikeObserver(this);
        likeNotification = new String();
    }


    /**
     * This user likes the picture of User user situated on the picturePos in the list.
     * @param user
     * @param picturePos
     */
    public void likePicture(User user, int picturePos){
        Picture likedPicture = user.pictures.get(picturePos);
        likedPicture.addLike();
        user.likeObserver.update(this, picturePos);
    }

    /**
     * This user unlikes the picture of User user situated on the picturePos in the list.
     * @param user
     * @param picturePos
     */
    public void unlikePicture(User user, int picturePos){
        Picture likedPicture = user.getPictures().get(picturePos);
        likedPicture.addLike();
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