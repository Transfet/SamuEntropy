package hu.gyulbro.webservice.service;

import hu.gyulbro.webservice.duplicate.UserBO;
import hu.gyulbro.webservice.entity.User;

import java.util.List;

public interface UserService {

    //listába kér le
    List<User> findAllUsers();
    List<String> getUserDataToList(String userID);

    //id-t kér le
    String getIDbyGoogleID (String googleID);
    String getUserID(String nickname);

    //változtat
    void addNewUser(UserBO user);
    void changeEmail (String userID, String email);
    String generateNewNick (String firstName, String lastName);

    //checkol
    String isPasswordCorrect (String email, String password);
    String googleAuth (String email, String googleID);

    //utility
    String generateUserID ();

}
