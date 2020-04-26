package com.example.architecture.accesData.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Timotei Molcut
 * This is the most important class because the user is most used in this application.
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
    @OneToMany(mappedBy = "owner", cascade=CascadeType.REMOVE)
    private List<Picture> pictures;

    /**
     * This is the observer that updates the string of notifications for likes.
     */
    @OneToOne(cascade = CascadeType.REMOVE)
    private LikeObserver likeObserver;

    /**
     * This is the string of notifications.
     */
    @Column
    private String likeNotification;


    /**
     * This is the list of friends for the current user.
     */
    @ManyToMany
    @JsonIgnore
    private List<User> friends;

    public User() {
    }

    /**
     * @param name
     * @param email
     */
    public User(String name, String email){
        this.name = name;
        this.email = email;
        this.likeNotification = new String();
        this.pictures = new ArrayList<Picture>();
        this.friends = new ArrayList<User>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * This method adds a picture to the current user.
     * @param picture
     */
    public void addPicture(Picture picture){
        this.pictures.add(picture);
    }

    /**
     * This method removes a picture from the current user.
     * @param picture
     */
    public void remPicture(Picture picture){
        this.pictures.remove(picture);
    }

    public List<Picture> getPictures(){
        return this.pictures;
    }

    /**
     * This method return the new notification and then it is reset to an empty notification.
     * @return
     */
    public String getLikeNotification(){
        String toReturn = this.likeNotification;
        this.likeNotification = "";//modify to empty notification
        return toReturn;
    }

    /**
     * This method changes the current notification after the update made by LikeObserver.
     * @param newNotification
     */
    public void changeLikeNotification(String newNotification){
        this.likeNotification = newNotification;
    }

    public List<User> getFriends(){
        return this.friends;
    }


    /**
     * This method add a new LikeObserver dedicated for this user.
     * @param observer
     */
    public void attachObserver(LikeObserver observer){
        observer.setUser(this);
        this.likeObserver = observer;
    }


    /**
     * This user likes the picture situated on the picturePos in the list of user.
     * @param user
     * @param picturePos
     */
    public Picture likePicture(User user, int picturePos){
        //a user can't like it's own pictures
        if(this.equals(user)){
            return null;
        }
        List<Picture> allPicsOfUser = user.getPictures();
        if(picturePos >= 0 && allPicsOfUser.size() > picturePos){
            Picture likedPicture = allPicsOfUser.get(picturePos);
            if(!likedPicture.getLikerList().contains(this)){
                likedPicture.addLike(this);
                user.likeObserver.update(this, picturePos);
                return likedPicture;
            } else {
                //user already liked this picture
                return null;
            }
        } else {
            //there is no picture on this position
            return null;
        }
    }

    /**
     * This user unlikes the picture situated on the picturePos in the list of user.
     * @param user
     * @param picturePos
     */
    public Picture unlikePicture(User user, int picturePos){
        if(this.equals(user)){
            //System.out.println("somebody tries to unlike his/her own picture");
            return null;
        }
        List<Picture> allPicsOfUser = user.getPictures();
        if(picturePos >= 0 && allPicsOfUser.size() > picturePos) {
            Picture likedPicture = allPicsOfUser.get(picturePos);
            if (likedPicture.getLikerList().contains(this)) {
                likedPicture.deleteLike(this);
                return likedPicture;
            } else {
                //System.out.println(this.getName() + " already unlikes " + likedPicture.getName());
                return null;
            }
        } else {
            //System.out.println(user+" doesn't have pic#"+picturePos);
            return null;
        }
    }


    /**
     * This user adds the other user as a friend only if the aren't the same and only if they are not already friends.
     * @param user
     */
    public void addFriend(User user){
        if(!this.equals(user) && !this.friends.contains(user)){
            this.friends.add(user);
            user.friends.add(this);
        }
    }

    /**
     * This user can remove his friend if it exists.
     * @param user
     */
    public void removeFriend(User user){
        if(!this.equals(user) && this.friends.contains(user)){
            this.friends.remove(user);
            user.friends.remove(this);
        }
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName()+
                '{'+
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}