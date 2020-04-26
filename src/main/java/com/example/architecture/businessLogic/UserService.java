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

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LikeObserverRepository observerRepository;

    @Autowired
    private PictureRepository pictureRepository;

    public boolean createUser(String name, String email, String type){
        User user;
        LikeObserver observer;
        if(StringUtils.isEmpty(name))
            return false;
        if(StringUtils.isEmpty(email) || !email.contains("@email.com"))
            return false;
        if(!type.equalsIgnoreCase("ENGINEER") &&
                !type.equalsIgnoreCase("MEDIC") &&
                !type.equalsIgnoreCase("TEACHER"))
            return false;
        user = new UserFactory().getUserByType(name, email, type);
        if(user.equals(null))
            return false;
        observer = new LikeObserver();
        observerRepository.save(observer);
        user.attachObserver(observer);
        userRepository.save(user);
        observerRepository.save(observer);
        return true;
    }

    public Iterable<User> findAllUsers(){
        return userRepository.findAll();
    }

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

    public boolean addPictureToUser(String userName, String picName){
        User user;
        Picture picture;
        if(StringUtils.isEmpty(userName))
            return false;
        if(StringUtils.isEmpty(picName) || !picName.contains(".jpg"))
            return false;
        user = userRepository.getUserByName(userName);
        picture = new Picture(user, picName);
        pictureRepository.save(picture);
        user.addPicture(picture);
        userRepository.save(user);
        return true;
    }

    public boolean addFriendToUser(Integer firstUserId, Integer secondUserId) {
        User firstFriend, secondFriend;
        if (userRepository.existsById(firstUserId)) {
            if (userRepository.existsById(secondUserId)) {
                firstFriend = userRepository.getUserByUserId(firstUserId);
                secondFriend = userRepository.getUserByUserId(secondUserId);
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
