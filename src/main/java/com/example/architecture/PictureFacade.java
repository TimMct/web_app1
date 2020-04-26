package com.example.architecture;

import com.example.architecture.accesData.entity.Picture;
import org.springframework.beans.factory.annotation.Autowired;

public class PictureFacade {

    PictureRepo  pictureRepo;

    public PictureFacade(PictureRepo pictureRepo){
        this.pictureRepo = pictureRepo;
    }

    public Picture findById(Integer id){
        return pictureRepo.findById(id);
    }

    public Picture findByName(String name){
        return pictureRepo.findByName(name);
    }

    public Picture findByUserName(String name){
        return pictureRepo.findByUserName(name);
    }

}
