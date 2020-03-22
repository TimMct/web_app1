package com.example.accessingdatamysql.arch;

import javax.persistence.*;

/**
 * @author Timotei Molcut
 * class needed to reprezent the ideea of a friendship between 2 users -- ManyToMany database relation
 */
@Entity
public class Friendship {
    /**
     * the primary key of the table is Integer fId
     */
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column
    private Integer fId;
    /**
     * the users between whom is a friend relationship
     * user1 sends friend request to user 2
     * user2 is able to accept or delete the request
     */
    @Column
    private Integer user1;
    @Column
    private Integer user2;

    /**
     * constructor
     */
    public Friendship(){}



}
