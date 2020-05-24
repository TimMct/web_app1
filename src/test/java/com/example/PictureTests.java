package com.example;
import com.example.architecture.PictureFacade;
import com.example.architecture.PictureRepo;
import com.example.architecture.accesData.entity.Picture;
import com.example.architecture.accesData.entity.User;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * @author Timotei Molcut
 * Class for picture basic tests. All the tests use mock methods.
 */
public class PictureTests {

    @Mock
    PictureRepo pictureRepo;
    @Rule
    public MockitoRule rule = MockitoJUnit.rule();
    private PictureFacade pictureFacade;
    private Picture dummy;

    @Before
    public void init(){
        pictureFacade = new PictureFacade(pictureRepo);
        dummy = new Picture(new User("first", "first@email.com", ""), "emi.jpg");
    }

    @Test
    public void testFindById(){
        when(pictureRepo.findById(1)).thenReturn(dummy);
        assertEquals(dummy, pictureFacade.findById(1));
        verify(pictureRepo).findById(1);
    }

    @Test
    public void testFindByName(){
        String picName = "emi.jpg";
        when(pictureRepo.findByName(picName)).thenReturn(dummy);
        assertEquals(dummy, pictureFacade.findByName(picName));
        verify(pictureRepo).findByName(picName);
    }

    @Test
    public void testFindByOwnerName(){
        String ownerName = "first";
        when(pictureRepo.findByUserName(ownerName)).thenReturn(dummy);
        assertEquals(dummy, pictureFacade.findByUserName(ownerName));
        verify(pictureRepo).findByUserName(ownerName);
    }

}
