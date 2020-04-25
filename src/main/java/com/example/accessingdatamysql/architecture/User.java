package com.example.accessingdatamysql.architecture;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
    private List<Picture> pictures;

    @Transient
    private LikeObserver likeObserver;

    @Transient
    private String likeNotification;


    @ManyToMany
    @JsonIgnore
    private List<User> friends;

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
        this.likeObserver = new LikeObserver(this);
        this.likeNotification = new String();
        this.friends = new ArrayList<User>();
        this.pictures = new ArrayList<Picture>();
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

    public void addPicture(Picture picture){
        this.pictures.add(picture);
    }

    public List<Picture> getPictures(){
        return this.pictures;
    }

    public String getLikeNotification(){
        String toReturn = this.likeNotification;
        this.likeNotification = "";//modify to empty notification
        return toReturn;
    }

    public void changeLikeNotification(String newNotification){
        this.likeNotification = newNotification;
    }

    public List<User> getFriends(){
        return this.friends;
    }

    /**
     * This user likes the picture situated on the picturePos in the list of user.
     * @param user
     * @param picturePos
     */
    public void likePicture(User user, int picturePos){
        if(this.equals(user)){
            System.out.println("somebody tries to like his/her own picture");
            return;
        }
        List<Picture> allPicsOfUser = user.getPictures();
        if(picturePos >= 0 && allPicsOfUser.size() > picturePos){
            Picture likedPicture = allPicsOfUser.get(picturePos);
            if(!likedPicture.getLikerList().contains(this)){
                likedPicture.addLike(this);
                user.likeObserver.update(this, picturePos);
            } else {
                System.out.println(this.getName()+" already liked "+likedPicture.getName());
            }
        } else {
            System.out.println(user+" doesn't have pic#"+picturePos);
        }
    }

    /**
     * This user unlikes the picture situated on the picturePos in the list of user.
     * @param user
     * @param picturePos
     */
    public void unlikePicture(User user, int picturePos){
        if(this.equals(user)){
            System.out.println("somebody tries to unlike his/her own picture");
            return;
        }
        List<Picture> allPicsOfUser = user.getPictures();
        if(picturePos >= 0 && allPicsOfUser.size() > picturePos) {
            Picture likedPicture = allPicsOfUser.get(picturePos);
            if (likedPicture.getLikerList().contains(this)) {
                likedPicture.deleteLike(this);
            } else {
                System.out.println(this.getName() + " already unlikes " + likedPicture.getName());
            }
        } else {
            System.out.println(user+" doesn't have pic#"+picturePos);
        }
    }


    public void addFriend(User user){
        if(!this.equals(user) && !this.friends.contains(user)){
            this.friends.add(user);
            user.friends.add(this);
        }
    }

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