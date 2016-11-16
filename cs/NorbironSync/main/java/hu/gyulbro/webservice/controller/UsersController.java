package com.example.controller;
import com.example.duplicate.Account;
import com.example.entity.Accounts;
import com.example.entity.Users;
import com.example.duplicate.User;
import com.example.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController(value = "users")
public class UsersController{

    private UsersService usersService;

    @Autowired
    public void setUsersService(UsersService usersService) {
        this.usersService = usersService;
    }

    @RequestMapping(value = "getusers",method = RequestMethod.GET)
    @ResponseBody
    public List<User> getUsers(){


        List<User> value = new ArrayList<>();
        List<Users> users = usersService.findAllUsers();
        for(Users u:users){
            User mappedUser = new User();
            mappedUser.setName(u.getName());

            value.add(mappedUser);
        }

        return value;
    }

    @RequestMapping(value = "createusers",method = RequestMethod.POST)
    @ResponseBody
    public void createUsers(@RequestBody User user){

        usersService.addNewWebUsers(user);

    }

}
