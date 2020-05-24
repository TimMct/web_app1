package com.example.architecture.controller;

import com.example.architecture.accesData.entity.Picture;
import com.example.architecture.businessLogic.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Timotei Molcut
 * class needed to manage the access to endpoints for the picture.
 */
@RestController
@RequestMapping(path = "/picture")
public class PictureController {

    /**
     * This will be used to open the picture from the machine.
     */
    private String relativePath = "/src/main/resources/pictures";

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

    /**
     * Get the picture providing it's name.
     * @param picName
     * @return
     */
    @PostMapping(path = "/getByName")
    public Picture getByName(String picName){
        return pictureService.getPicByName(picName);
    }

    /**
     * Delete all pictures.
     */
    @GetMapping(path = "/deleteAll")
    public void deleteAllPics(){
        pictureService.deleteAll();
    }

    /**
     *
     * @param email
     * @return
     */
    @PostMapping(path = "/getByUserEmail", consumes = {"application/json"})
    public List<Picture> getByUserName(@RequestBody String email){
        return pictureService.getPicByUserEmail(email);
    }

}
