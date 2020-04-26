package com.example.architecture;

import com.example.architecture.accesData.entity.Picture;

/**
 * @author TimoteiMolcut
 * This implements the facade pattern for tests. All these methods will encapsulate the methods of PictureRepo.
 */
public class PictureFacade {

    /**
     * This will be mocked.
     */
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
