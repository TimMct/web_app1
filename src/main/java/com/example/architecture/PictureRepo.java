package com.example.architecture;


import com.example.architecture.accesData.entity.Picture;
import org.springframework.stereotype.Repository;

/**
 * This interface mocks the abilities of PictureRepository.
 */
@Repository
public interface PictureRepo {

    Picture findById(Integer id);

    Picture findByName(String name);

    Picture findByUserName(String name);

}