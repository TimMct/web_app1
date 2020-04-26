package com.example.architecture.businessLogic;

import com.example.architecture.accesData.entity.Picture;
import com.example.architecture.accesData.repo.PictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author TimoteiMolcut
 * This class is needed to manage all the verification of managing the data received from the controller.
 * It needs a pictureRepository to store the data in the data base.
 */
@Service
public class PictureService {

    /**
     * This field is auto-instantiate at run-time because it's a bean.
     */
    @Autowired
    private PictureRepository pictureRepository;

    /**
     * Get all the picture.
     * @return
     */
    public Iterable<Picture> findAllPictures(){
        return pictureRepository.findAll();
    }

    /**
     * Get the picture providing it's name.
     * @param picName
     * @return
     */
    public Picture getPicByName(String picName){
        return pictureRepository.getPictureByName(picName);
    }

    /**
     * Delete all the picture existent.
     */
    public void deleteAll(){
        pictureRepository.deleteAll();
    }

    /**
     * Get picture providing the name of it's owner.
     * @param userName
     * @return
     */
    public Picture getPicByUserName(String userName){
        return pictureRepository.getPictureByOwnerName(userName);
    }

}
