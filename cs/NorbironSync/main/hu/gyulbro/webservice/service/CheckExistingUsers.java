package hu.gyulbro.webservice.service;

import hu.gyulbro.webservice.duplicate.UserBO;
import hu.gyulbro.webservice.entity.User;
import hu.gyulbro.webservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CheckExistingUsers {

    private UserRepository userRepository;

    @Autowired
    public void setUsersRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private String nickname;
    private String email;

    public CheckExistingUsers() {
    }

    public CheckExistingUsers(String nickname, String email) {
        this.nickname = nickname;
        this.email = email;
    }

    public String checkNickAndMail() {
        List<User> users = findUsers();

        if (isNickNameExisting(this.nickname)) {
            return "nickname already existing";
        } else if (isEmailExisting(this.email)) {
            return "Email already existing";
        }
        return "fine";
    }

    private List<User> findUsers() {
        List<User> users = userRepository.findAll();
        return users;
    }

    public boolean isNickNameExisting(String nickname) {
        List<User> users = findUsers();
        for (User user : users) {
            if (user.getNickname().equals(nickname)) {
                return true;
            }
        }
        return false;
    }

    public boolean isUserIDExisting(String userID) {
        List<User> users = findUsers();
        for (User user : users) {
            if (user.getUserID().equals(userID)) {
                return true;
            }
        }
        return false;
    }

    public boolean isEmailExisting(String email) {
        List<User> users = findUsers();
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    public boolean isGoogleIDExisting(String googleID) {
        List<User> users = findUsers();
        for (User user : users) {
            if (user.getGoogleID().equals(googleID)) {
                return true;
            }
        }
        return false;
    }
}
