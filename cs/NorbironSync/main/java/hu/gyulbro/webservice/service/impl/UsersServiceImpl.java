package com.example.service.impl;


import com.example.duplicate.Account;
import com.example.entity.Accounts;
import com.example.entity.Users;
import com.example.duplicate.User;
import com.example.repository.UsersRepository;
import com.example.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {

    private UsersRepository usersRepository;


    @Autowired
    public void setUsersRepository(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }



    @Override
    public List<Users> findAllUsers(){
        return usersRepository.findAll();

    }


    @Override
    public void addNewWebUsers(User user){
        Users users=new Users();
        users.setAge(user.getAge());
        users.setName(user.getName());


        List<Accounts> accounts=new ArrayList<>();
        for(Accounts acc:user.getAccountList())
        {
            Accounts storedAccountInstance = new Accounts();
            storedAccountInstance.setAccountNumber(acc.getAccountNumber());
            storedAccountInstance.setCurrency(acc.getCurrency());
            storedAccountInstance.setAccountType(acc.getAccountType());
            storedAccountInstance.setUser(acc.getUser());
            accounts.add(storedAccountInstance);

        }
        users.setAccountsList(accounts);



        usersRepository.save(users);
    }



}
