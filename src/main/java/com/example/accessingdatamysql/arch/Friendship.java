package com.example.accessingdatamysql.arch;

import javax.persistence.*;

@Entity
public class Friendship {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column
    private Integer fId;
    @Column
    private Integer user1;
    @Column
    private Integer user2;

    public Friendship(){}



}
