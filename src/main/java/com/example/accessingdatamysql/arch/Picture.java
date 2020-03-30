package com.example.accessingdatamysql.arch;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import javax.swing.*;

/**
 * @author Timotei Molcut
 * class needed to reprezent a picture by storing the absolute path to the picture file stored on the current machine
 */
@Entity
public class Picture extends PictureObserver{
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

    /**
     * empty constructor for @Entity
     */
    public Picture(){}

    /**
     * constructor for test class
     * @param picId primary key
     * @param owner foreign key
     * @param name relative path
     * @param nrOfLikes the level of appreciation for the picture
     */
    public Picture(Integer picId, User owner, String name, Integer nrOfLikes){
        this.picId = picId;
        this.owner = owner;
        this.owner.getPictures().add(this);
        this.name = name;
        this.nrOfLikes = nrOfLikes;
    }

    public Integer getPicId() {
        return picId;
    }

    public User getOwner(){
        return owner;
    }

    public String getName() {
        return name;
    }

    public Integer getNrOfLikes() {
        return nrOfLikes;
    }

    public void setPicId(Integer picId) {
        this.picId = picId;
    }

    public void setOwner(User owner){
        this.owner = owner;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNrOfLikes(Integer nrOfLikes) {
        this.nrOfLikes = nrOfLikes;
    }

    /**
     * method needed to open a picture in a JFrame window
     * here the absolute path for the file is saved, only the name is dynamic
     * @param void
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
    public void update(){
        System.out.println("Owner's data is: "+this.owner.getName()+" "+this.owner.getEmail());
    }
}
