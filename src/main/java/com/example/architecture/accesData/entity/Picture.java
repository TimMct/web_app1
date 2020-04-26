package com.example.architecture.accesData.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Timotei Molcut
 * class needed to reprezent a picture by storing the absolute path to the picture file stored on the current machine
 */
@Entity
public class Picture {
    /**
     * primary key for picture table
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer picId;

    /**
     * relation with the user table; the user can have many pictures e.g. OneToMany
     * a picture cannot have a null owner
     */
    @ManyToOne
    @JoinColumn(nullable = false)
    @JsonIgnore
    private User owner;

    /**
     * the name of the picture file, e.g. the relative path to the application folder (the absolute path is saved)
     */
    @Column(nullable = false)
    private String name;
    /**
     * a picture has a number of likes
     * a user can grow or decrease this value for another's user picture
     */
    @Column
    private Integer nrOfLikes;

    @OneToMany(cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<User> likerList;


    /**
     * empty constructor for @Entity
     */
    public Picture(){}

    public Picture(User owner, String name){
        this.owner = owner;
        this.name = name;
        this.nrOfLikes = 0;
        this.likerList = new ArrayList<User>();
    }

    public List<User> getLikerList(){
        return likerList;
    }

    public User getOwner(){
        return owner;
    }

    public String getName() {
        return name;
    }


    public void setOwner(User owner){
        this.owner = owner;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addLike(User user){
        this.nrOfLikes++;
        this.likerList.add(user);
    }

    public void deleteLike(User user){
        this.nrOfLikes--;
        this.likerList.remove(user);
    }

    public Integer getNrOfLikes(){
        return this.nrOfLikes;
    }


    /**
     * method needed to open a picture in a JFrame window
     * here the absolute path for the file is saved, only the name is dynamic
     * @return void
     */
    public void openPic(){
        JFrame frame = new JFrame();
        ImageIcon icon = new ImageIcon("D:\\Facultate\\An3\\Sem2\\PS\\web-app\\pictures\\" + name);
        JLabel label = new JLabel(icon);
        frame.add(label);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }


    @Override
    public String toString() {
        return "Picture{" +
                "name='" + name + '\'' +
                ", nrOfLikes=" + nrOfLikes +
                " like/likes}";
    }
}
