package com.example;

import com.example.architecture.UserFacade;
import com.example.architecture.UserRepo;
import com.example.architecture.accesData.entity.Picture;
import com.example.architecture.accesData.entity.User;
import com.example.architecture.accesData.entity.UserFactory;
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
 * class for user crud tests
 */

public class UserTests {

    private User dummyEngineer;
    private User dummyMedic;
    private User dummyTeacher;

    @Mock
    UserRepo userRepo;
    @Rule
    public MockitoRule rule = MockitoJUnit.rule();
    private UserFacade userFacade;




    @Before
    public void init(){
        dummyEngineer = new UserFactory().getUserByType("first", "first@email.com", "engineer");
        dummyMedic = new UserFactory().getUserByType("second", "second@email.com", "medic");
        dummyTeacher = new UserFactory().getUserByType("third", "third@email.com", "teacher");
        userFacade = new UserFacade(userRepo);
    }

    @Test
    public void findById(){
        when(userRepo.findById(1)).thenReturn(dummyEngineer);
        assertEquals(dummyEngineer, userFacade.findById(1));
        verify(userRepo).findById(1);
    }

    @Test
    public void createWrongUser(){
        //this will be null because of bad credentials
        User badCredentials = new UserFactory().getUserByType("aha", "aha@email.co", "blabla");
        when(userRepo.createUser("aha", "aha@email.co", "blabla")).thenReturn(badCredentials);
        assertEquals(null, userFacade.createUser("aha", "aha@email.co", "blabla"));
        verify(userRepo).createUser("aha", "aha@email.co", "blabla");
    }

    @Test
    public void updateName(){
        User updatedMedic = new UserFactory().getUserByType("2nd", "second@email.com", "medic");
        when(userRepo.updateName(dummyMedic.getName(), updatedMedic.getName())).thenReturn(updatedMedic);
        assertEquals(updatedMedic, userFacade.updateName(dummyMedic.getName(), updatedMedic.getName()));
        verify(userRepo).updateName(dummyMedic.getName(), updatedMedic.getName());
    }


    @Test
    public void updateEmail(){
        User updatedMedic = new UserFactory().getUserByType("second", "2nd@email.com", "medic");
        when(userRepo.updateEmail(dummyMedic.getEmail(), updatedMedic.getEmail())).thenReturn(updatedMedic);
        assertEquals(updatedMedic, userFacade.updateEmail(dummyMedic.getEmail(), updatedMedic.getEmail()));
        verify(userRepo).updateEmail(dummyMedic.getEmail(), updatedMedic.getEmail());
    }

    @Test
    public void addPicture(){
        String picName = "halabalula.jpg";
        dummyTeacher.addPicture(new Picture(dummyTeacher, picName));
        when(userRepo.addPicture(picName)).thenReturn(dummyTeacher);
        assertEquals(dummyTeacher, userFacade.addPicture(picName));
        verify(userRepo).addPicture(picName);
    }

    @Test
    public void addFriend(){
        dummyMedic.addFriend(dummyEngineer);
        when(userRepo.addFriend(dummyEngineer)).thenReturn(dummyMedic);
        assertEquals(dummyMedic, userFacade.addFriend(dummyEngineer));
        verify(userRepo).addFriend(dummyEngineer);
    }


    @Test
    public void likePicture(){
        dummyEngineer.likePicture(dummyTeacher, 0);
        when(userRepo.likePicture(dummyTeacher, 0)).thenReturn(dummyEngineer);
        assertEquals(dummyEngineer, userFacade.likePicture(dummyTeacher, 0));
        verify(userRepo).likePicture(dummyTeacher, 0);
    }
}
