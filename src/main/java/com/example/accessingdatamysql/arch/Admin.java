package com.example.accessingdatamysql.arch;

import javax.persistence.Entity;
import java.util.List;

/**
 * @author Timotei Molcut
 * class for representing the model of an administrator of the app
 */
//@Entity
public class Admin {
    /**
     * the admin has a list of users that he can watch over
     */
    private List<User> users;
    /**
     * the admin has a list of all pictures existing in the database
     */
    private List<Picture> pictures;
}
