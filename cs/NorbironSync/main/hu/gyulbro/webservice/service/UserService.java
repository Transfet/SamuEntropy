package hu.gyulbro.webservice.service;

import hu.gyulbro.webservice.duplicate.UserBO;
import hu.gyulbro.webservice.entity.User;

import java.util.List;

public interface UserService {

    List<User> findAllUsers();

    void addNewUser(UserBO user);

    String getUserID(String nickname);

    UserBO getBOinstance(String userID);

    List<String> getUserDataToList(String userID);

    String generateUserID ();

    String isPasswordCorrect (String email, String password);

    String googleAuth (String email, String googleID);

    String getIDbyGoogleID (String googleID);

    String generateNewNick (String firstName, String lastName);

    void changeEmail (String userID, String email);

}
