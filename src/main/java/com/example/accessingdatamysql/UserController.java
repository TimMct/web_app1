package com.example.accessingdatamysql;

import com.example.accessingdatamysql.architecture.Picture;
import com.example.accessingdatamysql.architecture.PictureRepository;
import com.example.accessingdatamysql.architecture.User;
import com.example.accessingdatamysql.architecture.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Timotei Molcut
 * class needed to create the crud endpoints for user table
 */
@RestController
@RequestMapping(path="/user")
public class UserController {
    /**
     * bean for user
     */
    @Autowired
    private UserRepository userRepository;
    /**
     * bean for picture
     */
    @Autowired
    private PictureRepository pictureRepository;


    /**
     * create endpoint
     * @param name
     * @param email
     * @return the message of succes/fail
     */
    @PostMapping(path="/add")
    public  String addNewUser ( String name,  String email) {
        User n = new User();

        if(name.equals(null) || name.equals(""))
            return "Must enter a valid name.";
        else
            n.setName(name);
        if(email.equals(null) || email.equals("") || !email.contains("@") || !email.contains("."))
            return "Must enter an valid email.";
        else
            n.setEmail(email);
        userRepository.save(n);
        return "User created.";
    }

    /**
     * read endpoint
     * @return all the user in the database
     */
    @GetMapping(path="/all")
    public  Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * update a user's name
     * @param oldName identifies the user to be modified
     * @param newName the new value for the name
     * @return message of success/fail
     */
    @PostMapping(path="/updateName")
    public String updateUserName(String oldName, String newName){
        Iterable<User> allUsers = userRepository.findAll();
        if(newName.equals(null) || newName.equals(""))
            return "Must enter a new valid name.";
        for(User u : allUsers){
            if(u.getName().equals(oldName)){
                u.setName(newName);
                userRepository.save(u);
                return "Name updated.";
            }
        }
        return "No user has the name:"+oldName+".";
    }

    /**
     * update a uses's email
     * @param oldEmail identifies the user to be modified
     * @param newEmail the new value for the email
     * @return message of success/fail
     */
    @PostMapping(path="/updateEmail")
    public String updateUserEmail(String oldEmail, String newEmail){
        Iterable<User> allUsers = userRepository.findAll();
        if(newEmail.equals(null) || newEmail.equals("") || !newEmail.contains("@") || !newEmail.contains("."))
            return "Must enter a new valid email.";
        for(User u : allUsers){
            if(u.getEmail().equals(oldEmail)){
                u.setEmail(newEmail);
                userRepository.save(u);
                return "Email updated.";
            }
        }
        return "No user has the email:"+oldEmail+".";
    }

    /**
     * delete a user knowing it's id
     * @param id the primary key of the user
     * @return message of success/fail
     */
    @PostMapping(path="/delete")
    public String delete(Integer id){
        if(userRepository.existsById(id)){
            userRepository.deleteById(id);
            return "User deleted.";
        }
        else
            return "Non-existent user.";
    }

    /**
     * delete all users
     * @return message of success
     */
    @GetMapping(path="/deleteAll")
    public String deleteAll(){
        userRepository.deleteAll();
        return "Users deleted.";
    }

    //---------------pictures-----------------
    //create

    /*public String addPicture(Integer userId, Integer picId){
        User user;
        Picture picture;
        if(userRepository.existsById(userId) && pictureRepository.existsById(picId))
    }*/

    /**
     * add a picture to an existing user found by an id, the name is given to the picture
     * if the user doesn't exists, e.g. the id is invalid, then nothing is done to the database
     * @param userId identifies the user if it exists
     * @param picName the file name
     * @return message of success/fail
     */
    @PostMapping(path = "/addPicture")
    public String addPicture(Integer userId, String picName){
        User user;
        Picture picture = new Picture();
        picture.setName(picName);
        if(userRepository.existsById(userId)){
            user = userRepository.findById(userId).orElse(null);

            picture.setOwner(user);
            pictureRepository.save(picture);

            user.getPictures().add(picture);
            userRepository.save(user);

            return "Picture "+picName+" was added to user named "+user.getName();
        }
        else
            return "There is no user with id "+userId;
    }

}