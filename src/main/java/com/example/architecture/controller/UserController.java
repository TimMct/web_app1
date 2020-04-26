package com.example.architecture.controller;

import com.example.architecture.accesData.entity.User;
import com.example.architecture.businessLogic.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Timotei Molcut
 * class needed to create the crud endpoints for user table
 */
@RestController
@RequestMapping(path="/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     *
     * @param name
     * @param email
     * @param type
     * @return
     */
    @PostMapping(path="/add")
    public String addNewUser (String name, String email, String type) {
        boolean created = userService.createUser(name, email, type);
        if(created){
            return "User was created.";
        } else {
            return "Something went wrong. User can't be created.";
        }
    }

    /**
     * read endpoint
     * @return all the user in the database
     */
    @GetMapping(path="/all")
    public  Iterable<User> getAllUsers() {
        return userService.findAllUsers();
    }

    /**
     * update a user's name
     * @param oldName identifies the user to be modified
     * @param newName the new value for the name
     * @return message of success/fail
     */
    @PostMapping(path="/updateName")
    public String updateUserName(String oldName, String newName) {
        boolean updated = userService.updateName(oldName, newName);
        if(updated){
            return "Name was updated.";
        } else {
            return "Something went wrong. Name can't be updated.";
        }
    }

    /**
     * update a uses's email
     * @param oldEmail identifies the user to be modified
     * @param newEmail the new value for the email
     * @return message of success/fail
     */
    @PostMapping(path="/updateEmail")
    public String updateUserEmail(String oldEmail, String newEmail){
        boolean updated = userService.updateEmail(oldEmail, newEmail);
        if(updated){
            return "Email was updated.";
        } else {
            return "Something went wrong. Email can't be updated.";
        }
    }

    /**
     * delete a user knowing it's id
     * @param id the primary key of the user
     * @return message of success/fail
     */
    @PostMapping(path="/delete")
    public String delete(Integer id){
        boolean deleted = userService.deleteById(id);
        if(deleted){
            return "User was deleted.";
        } else {
            return "Something went wrong. User doesn't exist.";
        }

    }

    /**
     * delete all users
     * @return message of success
     */
    @GetMapping(path="/deleteAll")
    public String deleteAll(){
        userService.deleteAll();
        return "Users deleted.";
    }


    /**
     *
     * @param userName
     * @param picName
     * @return
     */
    @PostMapping(path = "/addPicture")
    public String addPicture(String userName, String picName){
        boolean added = userService.addPictureToUser(userName, picName);
        if(added){
            return "Picture was added successfully.";
        } else {
            return "Something went wrong.";
        }

    }

    @PostMapping(path = "/remPicture")
    public String remPicture(String userName, String picName){
        boolean removed = userService.removePictureFromUser(userName, picName);
        if(removed){
            return "Picture was removed";
        } else {
            return "Something went wrong.";
        }
    }

    /**
     *
     * @param firstUserId
     * @param secondUserId
     * @return
     */
    @PostMapping(path = "/addFriend")
    public String addFriend(Integer firstUserId, Integer secondUserId){
        boolean added = userService.addFriendToUser(firstUserId, secondUserId);
        if(added){
            return "Friendship was made successfully.";
        } else {
            return "Something went wrong.";
        }
    }

    @PostMapping(path = "/remFriend")
    public String remFriend(Integer firstUserId, Integer secondUserId){
        boolean removed = userService.removeFriend(firstUserId, secondUserId);
        if(removed){
            return "Friend was removed successfully.";
        } else {
            return "Something went wrong.";
        }
    }

    @PostMapping(path = "/likePicture")
    public String likePicture(Integer firstUserId, Integer secondUserId, Integer picPos){
        boolean liked = userService.likePic(firstUserId, secondUserId, picPos);
        if(liked){
            return "Picture was liked successfully.";
        } else {
            return "Something went wrong.";
        }
    }

    @PostMapping(path = "/unlikePicture")
    public String unlikePicture(Integer firstUserId, Integer secondUserId, Integer picPos){
        boolean unlike = userService.unlikePic(firstUserId, secondUserId, picPos);
        if(unlike){
            return "Picture was unliked successfully.";
        } else {
            return "Something went wrong.";
        }
    }
}