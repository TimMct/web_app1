package com.example.webapp;

import com.example.accessingdatamysql.arch.Picture;
import com.example.accessingdatamysql.arch.PictureRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;

/**
 * @author Timotei Molcut
 * class for picture crud tests
 */
//@SpringBootTest
public class PictureTests {
    //@Autowired
    private PictureRepository pictureRepository;

    @Test
    public void addPicture(){
        Picture inserted;
        long countFirst;
        inserted = new Picture();
        countFirst = pictureRepository.count();
        pictureRepository.save(inserted);
        assertEquals(countFirst + 1, pictureRepository.count());
    }
    @Test
    public void deletePicture() {
        long countFirst;
        Picture inserted;
        inserted = new Picture();
        countFirst = pictureRepository.count();
        pictureRepository.save(inserted);
        pictureRepository.deleteById(2);
        assertEquals(countFirst, pictureRepository.count());
    }

    @Test
    public void retrievePicture() {
        Picture inserted, retrieved;
        inserted = new Picture();
        pictureRepository.save(inserted);
        retrieved = pictureRepository.findById(1).orElse(null);
        assertEquals(inserted, retrieved);
    }
}
