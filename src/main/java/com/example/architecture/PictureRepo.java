package com.example.architecture;


import com.example.architecture.accesData.entity.Picture;
import org.springframework.stereotype.Repository;

/**
 * interface for test
 */
@Repository
public interface PictureRepo {

    Picture findById(Integer id);

    Picture findByName(String name);

    Picture findByUserName(String name);

}