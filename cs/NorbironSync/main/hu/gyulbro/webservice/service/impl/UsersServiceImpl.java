package hu.gyulbro.webservice.service.impl;

import hu.gyulbro.webservice.entity.Accounts;
import hu.gyulbro.webservice.entity.Users;
import hu.gyulbro.webservice.repository.UsersRepository;
import hu.gyulbro.webservice.service.UsersService;
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
    public List<Users> findAllUsers() {
        return usersRepository.findAll();

    }

    public void addNewWebUsers(Users user) {
        Users users = new Users();
      //  users.setAge(user.getAge());
        users.setName(user.getName());

        List<Accounts> accounts = new ArrayList<>();
        /*
        for (Accounts acc : user.getAccountList()) {

            Accounts storedAccountInstance = new Accounts();
            storedAccountInstance.setAccountNumber(acc.getAccountNumber());
            storedAccountInstance.setCurrency(acc.getCurrency());
            storedAccountInstance.setAccountType(acc.getAccountType());
            storedAccountInstance.setUser(acc.getUser());
            accounts.add(storedAccountInstance);

        }
        users.setAccountsList(accounts);
    */
        usersRepository.save(users);
    }
}
