package com.example.entity;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "users")
public class Users {

    public static final String ID_COLUMN_NAME = "User_Id";
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = ID_COLUMN_NAME)
    private long id;
    private String name;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
