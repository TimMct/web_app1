package com.example.architecture.controller;

import com.example.architecture.accesData.entity.Picture;
import com.example.architecture.businessLogic.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Timotei Molcut
 * class needed to manage the access to endpoints for the picture
 * for the path http://localhost:8080/picture
 */
@RestController
@RequestMapping(path = "/picture")
public class PictureController {

    @Autowired
    private PictureService pictureService;

    /**
     * retrieve all pictures from database
     * @return the json file with all picture objects
     */
    @GetMapping(path = "/all")
    public Iterable<Picture> getAllPictures(){
        return pictureService.findAllPictures();
    }


    @PostMapping(path = "/getByName")
    public Picture getPicName(String picName){
        return pictureService.getPicByName(picName);
    }


    @GetMapping(path = "/deleteAll")
    public void deleteAllPics(){
        pictureService.deleteAll();
    }

//    /**
//     * incomplete method
//     * this endpoint intends to open a JFrame window with the picture
//     * @return message
//     */
//    @PostMapping(path = "/show")
//    public String show(){
//
//        //JFrame frame = new JFrame(name);
//        //ImageIcon icon = new ImageIcon("D:\\Facultate\\An3\\Sem2\\PS\\web-app\\pictures\\" + name);
//        //JLabel label = new JLabel(icon);
//        //frame.add(label);
//        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        //frame.pack();
//        //frame.setVisible(true);
//        /*if(pictureRepository.count() > 0L){
//            Iterable<Picture> allPics = pictureRepository.findAll();
//            for(Picture p : allPics){
//                //p.openPic();
//                //System.out.println(p.getName());
//                break;
//
//            }
//        }*/
//        return "OK";
//    }


}
