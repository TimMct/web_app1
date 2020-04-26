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

    private String fullPathPicture = "D:\\Facultate\\An3\\Sem2\\PS\\web-app\\pictures\\";

    @Autowired
    private PictureService pictureService;

    /**
     * retrieve all pictures from database
     * @return the json file with all picture objects
     */
    @GetMapping(path = "/getAll")
    public Iterable<Picture> getAllPictures(){
        return pictureService.findAllPictures();
    }

    @PostMapping(path = "/getByName")
    public Picture getByName(String picName){
        return pictureService.getPicByName(picName);
    }

    @GetMapping(path = "/deleteAll")
    public void deleteAllPics(){
        pictureService.deleteAll();
    }

    @PostMapping(path = "/getByUserName")
    public Picture getByUserName(String userName){
        return pictureService.getPicByUserName(userName);
    }

}
