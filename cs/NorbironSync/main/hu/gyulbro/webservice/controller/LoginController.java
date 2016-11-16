package hu.gyulbro.webservice.controller;

import hu.gyulbro.webservice.duplicate.UserBO;
import hu.gyulbro.webservice.service.CheckExistingUsers;
import hu.gyulbro.webservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class LoginController {

    private UserService usersService;

    @Autowired
    public void setUsersService(UserService userService) {
        this.usersService = userService;
    }

    @RequestMapping(value = "/login/{loginCredentials}", method = RequestMethod.GET)
    @ResponseBody
    List<String> loginAttempt(@PathVariable ArrayList<String> loginCredentials) {

        String validLogin = usersService.isPasswordCorrect(loginCredentials.get(0), loginCredentials.get(1));

        List<String> result = new ArrayList<>();

        if ("succeed".equals(validLogin)) {
            String userID = usersService.getUserID(loginCredentials.get(0));

            return usersService.getUserDataToList(userID);
        } else {
            result.add(validLogin);
        }

        return result;
    }

    @RequestMapping(value = "/register/{registrationCredentials}", method = RequestMethod.POST)
    @ResponseBody
    String createUser(@PathVariable ArrayList<String> registrationCredentials) {

        //nickname, email
        CheckExistingUsers checker = new CheckExistingUsers(registrationCredentials.get(0),
                registrationCredentials.get(1));

        String result = checker.checkNickAndMail();

        if (result.equals("fine")) {
            UserBO userToCreate = new UserBO(
                    registrationCredentials.get(0), //nickname
                    registrationCredentials.get(1), //email
                    registrationCredentials.get(2), //firstName
                    registrationCredentials.get(3),  //lastName
                    registrationCredentials.get(4)); //password
            String userId = usersService.generateUserID();
            userToCreate.setUserID(userId);
            usersService.addNewUser(userToCreate);

            return "succeed";
        } else {
            return result;
        }
    }

    @RequestMapping(value = "/google/{googleCredentials}", method = RequestMethod.POST)
    @ResponseBody
    List<String> googleAuth(@PathVariable ArrayList<String> registrationCredentials) {
        String nickname = registrationCredentials.get(0);
        String firstName = registrationCredentials.get(1);
        String lastName = registrationCredentials.get(2);
        String email = registrationCredentials.get(3);
        String googleID = registrationCredentials.get(4);

        String result = usersService.googleAuth(email, googleID);

        CheckExistingUsers checkExistingUsers = new CheckExistingUsers();

        boolean isNickValidated = false;

        Integer i = 0;

        List<String> userDataToPass = new ArrayList<>();

        while (!isNickValidated) {
            isNickValidated = checkExistingUsers.isNickNameExisting(nickname);

            if (isNickValidated) {
                nickname = usersService.generateNewNick(firstName, lastName + i.toString());
                isNickValidated = false;
                i++;
            } else {
                isNickValidated = true;
            }
        }

        if ("logged_in".equals(result)) {
            String userID = usersService.getIDbyGoogleID(googleID);

            userDataToPass = usersService.getUserDataToList(userID);
        } else if ("not_existing".equals(result)) {

            UserBO googleUserToCreate = new UserBO(
                    nickname, //nickname
                    email, //email
                    firstName, //firstName
                    lastName,  //lastName
                    null);//password
            googleUserToCreate.setUserID(usersService.generateUserID());
            googleUserToCreate.setGoogleID(googleID);

            usersService.addNewUser(googleUserToCreate);
        } else {
            String userID = usersService.getIDbyGoogleID(googleID);
            usersService.changeEmail(userID, email);

            userDataToPass = usersService.getUserDataToList(userID);
        }
        return userDataToPass;
    }
}
