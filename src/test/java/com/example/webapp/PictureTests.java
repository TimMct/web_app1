package com.example.webapp;

import com.example.accessingdatamysql.arch.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * @author Timotei Molcut
 * class for picture crud tests
 */
//@RunWith(MockitoJUnitRunner.class)
public class PictureTests {

    public Picture dummy;
    private PictureFacade pictureFacade;
    @Mock
    PictureRepo pictureRepo;

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Before
    public void setUp(){
        dummy = new Picture(1, new User(), "eminescu1.jpg", 100);
        pictureFacade = new PictureFacade(pictureRepo);
    }

    @Test
    public void testFindById(){

        when(pictureRepo.findById(1)).thenReturn(dummy);
        assertEquals(dummy, pictureFacade.findById(1));
        verify(pictureRepo).findById(1);

    }






    /*@Test
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
    }*/
}
