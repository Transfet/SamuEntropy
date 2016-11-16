package hu.gyulbro.webservice.service;

import hu.gyulbro.webservice.entity.Users;
import java.util.List;

public interface UsersService {

    List<Users> findAllUsers();

    void addNewWebUsers(Users usersRepository);
}

