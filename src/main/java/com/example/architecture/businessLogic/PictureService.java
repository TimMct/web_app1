package com.example.architecture.businessLogic;

import com.example.architecture.accesData.entity.Picture;
import com.example.architecture.accesData.repo.PictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PictureService {

    @Autowired
    private PictureRepository pictureRepository;

    public Iterable<Picture> findAllPictures(){
        return pictureRepository.findAll();
    }

    public Picture getPicByName(String picName){
        return pictureRepository.getPictureByName(picName);
    }

    public void deleteAll(){
        pictureRepository.deleteAll();
    }

}
