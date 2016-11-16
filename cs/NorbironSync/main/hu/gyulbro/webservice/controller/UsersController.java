package hu.gyulbro.webservice.controller;

import hu.gyulbro.webservice.entity.Users;
import hu.gyulbro.webservice.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController(value = "users")
public class UsersController {
    private UsersService usersService;

    @Autowired
    public void setUsersService(UsersService usersService) {
        this.usersService = usersService;
    }

    @RequestMapping(value = "getusers", method = RequestMethod.GET)
    @ResponseBody
    public List<Users> getUsers() {

        List<Users> value = new ArrayList<>();
        List<Users> users = usersService.findAllUsers();
        for (Users u : users) {
            Users mappedUser = new Users();
            mappedUser.setName(u.getName());

            value.add(mappedUser);
        }
        return value;
    }

    @RequestMapping(value = "createusers", method = RequestMethod.POST)
    @ResponseBody
    public void createUsers(@RequestBody Users user) {

        usersService.addNewWebUsers(user);

    }
}
