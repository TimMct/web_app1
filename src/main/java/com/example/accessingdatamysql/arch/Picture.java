package com.example.accessingdatamysql.arch;

import javax.imageio.ImageIO;
import javax.persistence.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

@Entity
public class Picture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer picId;

    /*@ManyToOne
    @JoinColumn(nullable = false)
    private User owner;
    */

    @Column(nullable = false)
    private String name;
    @Column
    private Integer nrOfLikes;

    public Picture(){}

    public Integer getPicId() {
        return picId;
    }

    /*public User getOwner(){
        return owner;
    }*/

    public String getName() {
        return name;
    }

    public Integer getNrOfLikes() {
        return nrOfLikes;
    }

    public void setPicId(Integer picId) {
        this.picId = picId;
    }

    /*public void setOwner(User owner){
        this.owner = owner;
    }*/

    public void setName(String name) {
        this.name = name;
    }

    public void setNrOfLikes(Integer nrOfLikes) {
        this.nrOfLikes = nrOfLikes;
    }

    public void openPic(){
        JFrame frame = new JFrame();
        ImageIcon icon = new ImageIcon("D:\\Facultate\\An3\\Sem2\\PS\\web-app\\pictures\\" + name);
        JLabel label = new JLabel(icon);
        frame.add(label);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    //exemplu de rulare
    /*
        Picture p = new Picture();
        p.setName("emi.jpg");
        p.openPic();
    */



    public void addLike(){
        nrOfLikes++;
    }

    public void removeLike(){
        nrOfLikes--;
    }
}
