package com.example.webapp;

import com.example.accessingdatamysql.UserController;
import com.example.accessingdatamysql.WebApplication;
import com.example.accessingdatamysql.arch.User;
import com.example.accessingdatamysql.arch.UserRepository;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;


/**
 * @author Timotei Molcut
 * class for user crud tests
 */
//@RunWith(value = SpringRunner.class)
//@SpringBootTest
public class UserTests {
    //@Autowired
    //private UserRepository userRepository;

    private String name;
    private String email;
    private UserController userController;

    @Before
    public void init(){
        name = "dummy";
        email = "dummy@email.com";
    }

    /*@Test
    public void testAddUser(){
        assertEquals("User created.", userController.addNewUser(name, email));
    }*/



/*
    @Test
    public void addUser(){
        User inserted;
        long countFirst;
        inserted = new User(1, "dummy", "dummy@email.com");
        countFirst = userRepository.count();
        userRepository.save(inserted);
        assertEquals(countFirst + 1, userRepository.count());
    }

    @Test
    public void deleteUser() {
        long countFirst;
        User inserted;
        inserted = new User(2, "dummy", "dummy@email.com");
        countFirst = userRepository.count();
        userRepository.save(inserted);
        userRepository.deleteById(2);
        assertEquals(countFirst, userRepository.count());
    }

    @Test
    public void retrieve(){
        User inserted, retrieved;
        inserted = new User(1, "dummy", "dummy@email.com");
        userRepository.save(inserted);
        retrieved = userRepository.findById(1).orElse(null);
        assertEquals(inserted, retrieved);
    }*/
}
