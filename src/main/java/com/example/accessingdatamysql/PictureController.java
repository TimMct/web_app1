package com.example.accessingdatamysql;

import com.example.accessingdatamysql.arch.Picture;
import com.example.accessingdatamysql.arch.PictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.*;

@RestController
@RequestMapping(path = "/picture")
public class PictureController {
    @Autowired
    private PictureRepository pictureRepository;


    @PostMapping(path = "/add")
    public String addPicture(String name){
        Picture p = new Picture();
        p.setName(name);
        p.setNrOfLikes(0);
        pictureRepository.save(p);
        return "Picture created.";
    }

    @GetMapping(path = "/all")
    public Iterable<Picture> getAllPictures(){
        return pictureRepository.findAll();
    }

    @PostMapping(path = "/show")
    public String show(){
        
        //JFrame frame = new JFrame(name);
        //ImageIcon icon = new ImageIcon("D:\\Facultate\\An3\\Sem2\\PS\\web-app\\pictures\\" + name);
        //JLabel label = new JLabel(icon);
        //frame.add(label);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.pack();
        //frame.setVisible(true);
        /*if(pictureRepository.count() > 0L){
            Iterable<Picture> allPics = pictureRepository.findAll();
            for(Picture p : allPics){
                //p.openPic();
                //System.out.println(p.getName());
                break;

            }
        }*/
        return "OK";
    }


}
