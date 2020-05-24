package com.example.architecture.accesData.repo;

import com.example.architecture.accesData.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Timotei Molcut
 * Interface needed to acces the database and performe crud opperations for users.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * Get the user, providing the id.
     * @param userId
     * @return
     */
    public User getUserByUserId(Integer userId);

    /**
     * Get the user, providing it's name.
     * @param name
     * @return
     */
    public User getUserByName(String name);

    /**
     * Get the user providing it's email.
     * @param email
     * @return
     */
    public User getUserByEmail(String email);

    boolean existsUserByEmail(String email);

    boolean existsUserByEmailAndPassword(String email, String password);

}