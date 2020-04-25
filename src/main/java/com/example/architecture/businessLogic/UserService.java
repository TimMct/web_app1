package com.example.architecture.businessLogic;

import com.example.architecture.accesData.entity.Picture;
import com.example.architecture.accesData.entity.User;
import com.example.architecture.accesData.UserFactory;
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
    private PictureRepository pictureRepository;

    public boolean createUser(String name, String email, String type){
        User user;
        if(StringUtils.isEmpty(name))
            return false;
        if(StringUtils.isEmpty(email) || !email.contains("@email.com"))
            return false;
        if(!type.equalsIgnoreCase("ENGINEER") &&
                !type.equalsIgnoreCase("MEDIC") &&
                !type.equalsIgnoreCase("TEACHER"))
            return false;

        user = new UserFactory().getUserByType(name, email, type);
        userRepository.save(user);
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
}
