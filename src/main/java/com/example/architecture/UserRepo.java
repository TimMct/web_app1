package com.example.architecture;

import com.example.architecture.accesData.entity.User;
import org.springframework.stereotype.Repository;

/**
 * @author TimoteiMolcut
 * This class will be mocked to simulate UserRepository.
 */
@Repository
public interface UserRepo {

    User findById(Integer id);

    User createUser(String name, String email, String type);

    User updateName(String oldName, String newName);

    User updateEmail(String oldEmail, String newEmail);

    User addPicture(String picName);

    User addFriend(User user);

    User likePicture(User user, Integer picPos);
}
