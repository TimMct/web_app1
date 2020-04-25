package com.example.architecture;


import com.example.architecture.accesData.entity.Picture;

/**
 * interface for test
 */
public interface PictureRepo {

    public Picture findById(Integer id);

}