package com.example.architecture.businessLogic;

import com.example.architecture.accesData.entity.LikeObserver;
import com.example.architecture.accesData.entity.Picture;
import com.example.architecture.accesData.entity.User;
import com.example.architecture.accesData.entity.UserFactory;
import com.example.architecture.accesData.repo.LikeObserverRepository;
import com.example.architecture.accesData.repo.PictureRepository;
import com.example.architecture.accesData.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author TimoteiMolcut
 * This class deals with all the operations made on user and it makes verifications
 * so the controller class isn't overloaded.
 */
@Service
public class UserService {
    /**
     * This fields deal with storing data in the data base.
     */
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LikeObserverRepository observerRepository;
    @Autowired
    private PictureRepository pictureRepository;

    /**
     * This method verifies the parameters to be ok the using a UserFactory it retrieves the user.
     * @param name
     * @param email
     * @param type
     * @return
     */
    public boolean createUser(String name, String email, String password, String type){
        User user;
        LikeObserver observer;
        if(StringUtils.isEmpty(name))
            return false;
        if(StringUtils.isEmpty(email) || (!email.contains("@yahoo.com") && !email.contains("@gmail.com")))
            return false;
        if(StringUtils.isEmpty(password)){
            return false;
        }
        if(!type.equalsIgnoreCase("ENGINEER") &&
                !type.equalsIgnoreCase("MEDIC") &&
                !type.equalsIgnoreCase("TEACHER"))
            return false;

        if(userRepository.existsUserByEmail(email)){
            return false;
        }

        user = new UserFactory().getUserByType(name, email, password, type);
        if(user.equals(null))
            return false;
        observer = new LikeObserver();
        observerRepository.save(observer);
        user.attachObserver(observer);
        userRepository.save(user);
        observerRepository.save(observer);
        return true;
    }


    public boolean findUser(String email, String password){
        return userRepository.existsUserByEmailAndPassword(email, password);
    }


    public List<Picture> getPicByUserEmail(String email){
        User u = userRepository.getUserByEmail(email);
        if(u.equals(null))
            return new ArrayList<Picture>();

        return u.getPictures();
    }


    public List<User> findAllDifferent(String email){
        List<User> allUsers = userRepository.findAll();
        for(User u : allUsers){
            if(u.getEmail().equals(email)){
                allUsers.remove(u);
                break;
            }
        }
        return allUsers;
    }



    public Iterable<User> findAllUsers(){
        return userRepository.findAll();
    }

    /**
     * Update the name of the user knowing it's old name.
     * @param oldName
     * @param newName
     * @return
     */
    public boolean updateName(String oldName, String newName){
        User user;
        if(StringUtils.isEmpty(oldName))
            return false;
        if(StringUtils.isEmpty(newName))
            return false;
        user = userRepository.getUserByName(oldName);
        user.setName(newName);
        userRepository.save(user);
        return true;
    }

    /**
     * Update the email of the user knowing it's old email.
     * @param oldEmail
     * @param newEmail
     * @return
     */
    public boolean updateEmail(String oldEmail, String newEmail){
        User user;
        if(StringUtils.isEmpty(oldEmail))
            return false;
        if(StringUtils.isEmpty(newEmail) || !newEmail.contains("@email.com"))
            return false;
        user = userRepository.getUserByEmail(oldEmail);
        user.setEmail(newEmail);
        userRepository.save(user);
        return true;
    }

    /**
     * Delete the user that has this id.
     * @param id
     * @return
     */
    public boolean deleteById(Integer id){
        if(userRepository.existsById(id)){
            userRepository.deleteById(id);
            return true;
        }
        else
            return false;
    }

    public void deleteAll(){
        userRepository.deleteAll();
    }


    public boolean addPictureToUser(String email, String picName){
        User user;
        Picture picture;
        if(StringUtils.isEmpty(email))
            return false;
        if(StringUtils.isEmpty(picName) || !picName.contains(".jpg"))
            return false;
        user = userRepository.getUserByEmail(email);
        picture = new Picture(user, picName);
        pictureRepository.save(picture);
        user.addPicture(picture);
        userRepository.save(user);
        return true;
    }

    /**
     * Remove the picture from the user.
     * @param userName
     * @param picName
     * @return
     */
    public boolean removePictureFromUser(String userName, String picName){
        User user;
        Picture picture;
        if(StringUtils.isEmpty(userName))
            return false;
        if(StringUtils.isEmpty(picName) || !picName.contains(".jpg"))
            return false;
        user = userRepository.getUserByName(userName);
        picture = pictureRepository.getPictureByName(picName);
        user.remPicture(picture);
        userRepository.save(user);
        return true;
    }

    /**
     *
     * @param firstEmail
     * @param secondEmail
     * @return
     */
    public boolean addFriendToUser(String firstEmail, String secondEmail) {
        User firstFriend, secondFriend;
        if (userRepository.existsUserByEmail(firstEmail)) {
            if (userRepository.existsUserByEmail(secondEmail)) {
                firstFriend = userRepository.getUserByEmail(firstEmail);
                secondFriend = userRepository.getUserByEmail(secondEmail);
                firstFriend.addFriend(secondFriend);
                userRepository.save(firstFriend);
                userRepository.save(secondFriend);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Remove a friend from the current user.
     * @param firstUserId
     * @param secondUserId
     * @return
     */
    public boolean removeFriend(Integer firstUserId, Integer secondUserId){
        User firstUser, secondUser;
        if(userRepository.existsById(firstUserId)){
            if(userRepository.existsById(secondUserId)){
                firstUser = userRepository.getUserByUserId(firstUserId);
                secondUser = userRepository.getUserByUserId(secondUserId);
                firstUser.removeFriend(secondUser);
                userRepository.save(firstUser);
                userRepository.save(secondUser);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * A user likes the picture of another user, providing the users ids and the position of the picture in
     * the second's user pictureList.
     * @param firstUserId
     * @param secondUserId
     * @param picPos
     * @return
     */
    public boolean likePic(Integer firstUserId, Integer secondUserId, Integer picPos){
        User firstUser, secondUser;
        Picture likedPic;
        if(userRepository.existsById(firstUserId)){
            if(userRepository.existsById(secondUserId)){
                firstUser = userRepository.getUserByUserId(firstUserId);
                secondUser = userRepository.getUserByUserId(secondUserId);
                likedPic = firstUser.likePicture(secondUser, picPos);
                if(likedPic.equals(null))
                    return false;
                pictureRepository.save(likedPic);
                userRepository.save(firstUser);
                userRepository.save(secondUser);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Remove the like if is existent.
     * @param firstUserId
     * @param secondUserId
     * @param picPos
     * @return
     */
    public boolean unlikePic(Integer firstUserId, Integer secondUserId, Integer picPos){
        User firstUser, secondUser;
        Picture unlikePic;
        if(userRepository.existsById(firstUserId)){
            if(userRepository.existsById(secondUserId)){
                firstUser = userRepository.getUserByUserId(firstUserId);
                secondUser = userRepository.getUserByUserId(secondUserId);
                unlikePic = firstUser.unlikePicture(secondUser, picPos);
                if(unlikePic.equals(null)){
                    return false;
                }
                pictureRepository.save(unlikePic);
                userRepository.save(firstUser);
                userRepository.save(secondUser);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

}
