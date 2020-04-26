package com.example.architecture;

import com.example.architecture.accesData.entity.User;

public class UserFacade {

    UserRepo userRepo;

    public UserFacade(UserRepo userRepo){
        this.userRepo = userRepo;
    }

    public User findById(Integer id){
        return this.userRepo.findById(id);
    }

    public User createUser(String name, String email, String type){
        return userRepo.createUser(name, email, type);
    }

    public User updateName(String oldName, String newName){
        return userRepo.updateName(oldName, newName);
    }

    public User updateEmail(String oldEmail, String newEmail){
        return userRepo.updateEmail(oldEmail, newEmail);
    }

    public User addPicture(String picName){
        return userRepo.addPicture(picName);
    }

    public User addFriend(User user){
        return userRepo.addFriend(user);
    }

    public User likePicture(User user, Integer picPos){
        return userRepo.likePicture(user, picPos);
    }
}
