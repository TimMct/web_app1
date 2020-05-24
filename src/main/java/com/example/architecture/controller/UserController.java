package com.example.architecture.controller;

import com.example.architecture.accesData.entity.*;
import com.example.architecture.businessLogic.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author Timotei Molcut
 * class needed to create the crud endpoints for user table
 */
@RestController
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     *
     * @param userWrapper
     * @return
     */
    @PostMapping(path = "/add", consumes = {"application/json"})
    public String addNewUser(@RequestBody UserWrapper userWrapper) {
        boolean created = userService.createUser(userWrapper.getName(), userWrapper.getEmail(), userWrapper.getPassword(), userWrapper.getType());
        if (created) {
            return "User was created.";
        } else {
            return "Something went wrong. User can't be created.";
        }
    }


    @PostMapping(path = "/find", consumes = {"application/json"})
    public String findUser(@RequestBody UserWrapper userWrapper){

        boolean found = userService.findUser(userWrapper.getEmail(), userWrapper.getPassword());
        if(found){
            return "Found.";
        }else {
            return "Not found.";
        }
    }

    /**
     *
     * @param userWrapper
     * @return
     */
    @PostMapping(path = "/getPicsOfUser", consumes = {"application/json"})
    public List<Picture> getByUserEmail(@RequestBody UserWrapper userWrapper){
        return userService.getPicByUserEmail(userWrapper.getEmail());
    }


    /**
     * Get all the existent users.
     *
     * @return all the user in the database
     */
    @PostMapping(path = "/allButOne", consumes = {"application/json"})
    public Iterable<User> getAllUsers(@RequestBody UserWrapper userWrapper) {
        return userService.findAllDifferent(userWrapper.getEmail());
    }

    /**
     * update a user's name
     *
     * @param oldName identifies the user to be modified
     * @param newName the new value for the name
     * @return message of success/fail
     */
    @PostMapping(path = "/updateName")
    public String updateUserName(String oldName, String newName) {
        boolean updated = userService.updateName(oldName, newName);
        if (updated) {
            return "Name was updated.";
        } else {
            return "Something went wrong. Name can't be updated.";
        }
    }

    /**
     * update a uses's email
     *
     * @param oldEmail identifies the user to be modified
     * @param newEmail the new value for the email
     * @return message of success/fail
     */
    @PostMapping(path = "/updateEmail")
    public String updateUserEmail(String oldEmail, String newEmail) {
        boolean updated = userService.updateEmail(oldEmail, newEmail);
        if (updated) {
            return "Email was updated.";
        } else {
            return "Something went wrong. Email can't be updated.";
        }
    }

    /**
     * delete a user knowing it's id
     *
     * @param id the primary key of the user
     * @return message of success/fail
     */
    @PostMapping(path = "/delete")
    public String delete(Integer id) {
        boolean deleted = userService.deleteById(id);
        if (deleted) {
            return "User was deleted.";
        } else {
            return "Something went wrong. User doesn't exist.";
        }

    }

    /**
     * delete all users
     *
     * @return message of success
     */
    @GetMapping(path = "/deleteAll")
    public String deleteAll() {
        userService.deleteAll();
        return "Users deleted.";
    }



    @PostMapping(path = "/addPicture", consumes = {"application/json"})
    public String addPicture(@RequestBody PictureWrapper pictureWrapper) {
        boolean added = userService.addPictureToUser(pictureWrapper.getEmail(), pictureWrapper.getPicName());
        if (added) {
            return "Picture was added successfully.";
        } else {
            return "Something went wrong.";
        }

    }

    /**
     * Remove the picture from the user.
     *
     * @param userName
     * @param picName
     * @return
     */
    @PostMapping(path = "/remPicture")
    public String remPicture(String userName, String picName) {
        boolean removed = userService.removePictureFromUser(userName, picName);
        if (removed) {
            return "Picture was removed";
        } else {
            return "Something went wrong.";
        }
    }

    /**
     *
     * @param friendWrapper
     * @return
     */
    @PostMapping(path = "/addFriend", consumes = {"application/json"})
    public String addFriend(@RequestBody FriendWrapper friendWrapper) {
        boolean added = userService.addFriendToUser(friendWrapper.getFirstEmail(), friendWrapper.getSecondEmail());
        if (added) {
            return "Friendship was made successfully.";
        } else {
            return "Something went wrong.";
        }
    }

    /**
     * Remove the friend from the surrent user.
     *
     * @param firstUserId
     * @param secondUserId
     * @return
     */
    @PostMapping(path = "/remFriend")
    public String remFriend(Integer firstUserId, Integer secondUserId) {
        boolean removed = userService.removeFriend(firstUserId, secondUserId);
        if (removed) {
            return "Friend was removed successfully.";
        } else {
            return "Something went wrong.";
        }
    }

    /**
     * Like another's user picture.
     *
     * @param firstUserId
     * @param secondUserId
     * @param picPos
     * @return
     */
    @PostMapping(path = "/likePicture")
    public String likePicture(Integer firstUserId, Integer secondUserId, Integer picPos) {
        boolean liked = userService.likePic(firstUserId, secondUserId, picPos);
        if (liked) {
            return "Picture was liked successfully.";
        } else {
            return "Something went wrong.";
        }
    }

    /**
     * Unlike another's user picture.
     *
     * @param firstUserId
     * @param secondUserId
     * @param picPos
     * @return
     */
    @PostMapping(path = "/unlikePicture")
    public String unlikePicture(Integer firstUserId, Integer secondUserId, Integer picPos) {
        boolean unlike = userService.unlikePic(firstUserId, secondUserId, picPos);
        if (unlike) {
            return "Picture was unliked successfully.";
        } else {
            return "Something went wrong.";
        }
    }
}