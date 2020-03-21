package com.example.accessingdatamysql;

import com.example.accessingdatamysql.arch.User;
import com.example.accessingdatamysql.arch.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    //create
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

    //read
    @GetMapping(path="/all")
    public  Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    //update
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

    //delete
    @PostMapping(path="/delete")
    public String delete(Integer id){
        if(userRepository.existsById(id)){
            userRepository.deleteById(id);
            return "User deleted.";
        }
        else
            return "Non-existent user.";
    }

    @GetMapping(path="/deleteAll")
    public String deleteAll(){
        userRepository.deleteAll();
        return "Users deleted.";
    }
}