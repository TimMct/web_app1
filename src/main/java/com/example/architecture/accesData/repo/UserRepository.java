package com.example.architecture.accesData.repo;

import com.example.architecture.accesData.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Timotei Molcut
 * interface needed to acces the database and performe crud opperations for users
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    public User getUserByUserId(Integer userId);

    public User getUserByName(String name);

    public User getUserByEmail(String email);
}