package com.example.accessingdatamysql.arch;

import javax.persistence.*;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer userId;
    @Column
    private String name;
    @Column
    private String email;

    /*@OneToMany
    private List<Picture> pictures;
*/

    //private List<User> friends;

    //empty constructor
    public User(){}


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
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    /*
    public List<Picture> getPictures(){
        return pictures;
    }

    public void likePicture(User u, Picture p){
        for(Picture pic: u.getPictures()){
            if(pic.equals(p))
                p.addLike();
        }
    }

    public void unlikePicture(User u, Picture p){
        for(Picture pic: u.getPictures()){
            if(pic.equals(p))
                p.removeLike();
        }
    }


    public void sendFR(User u){

    }
    */
}