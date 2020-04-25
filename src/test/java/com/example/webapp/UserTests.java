package com.example.webapp;

import com.example.architecture.controller.UserController;
import org.junit.Before;

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
