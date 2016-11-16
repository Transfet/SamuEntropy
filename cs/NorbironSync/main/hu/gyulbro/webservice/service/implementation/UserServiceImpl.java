package hu.gyulbro.webservice.service.implementation;

import hu.gyulbro.webservice.duplicate.UserBO;
import hu.gyulbro.webservice.entity.Node;
import hu.gyulbro.webservice.entity.User;
import hu.gyulbro.webservice.repository.UserRepository;
import hu.gyulbro.webservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public void setUsersRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<String> getUserDataToList(String userID) {
        List<User> users = findAllUsers();
        List<String> listToPass = new ArrayList<>();

        for (User user : users) {
            if (user.getUserID().equals(userID)) {
                listToPass.add(user.getNickname());
                listToPass.add(user.getFirstName());
                listToPass.add(user.getLastName());
                listToPass.add(user.getUserID());
            }
        }

        return listToPass;
    }

    @Override
    public String getIDbyGoogleID(String googleID) {
        List<User> users = findAllUsers();
        for (User user : users) {
            if (user.getGoogleID().equals(googleID)) {
                return user.getUserID();
            }
        }
        return null;
    }

    @Override
    public String getUserID(String nickname) {
        List<User> users = findAllUsers();
        for (User user : users) {
            if (user.getNickname().equals(nickname)) {
                return user.getUserID();
            }
        }
        return null;
    }

    @Override
    public void addNewUser(UserBO user) {
        User users = new User();
        users.setNickname(user.getNickname());
        users.setEmail(user.getEmail());
        users.setUserID(user.getUserID());
        users.setGoogleID(user.getGoogleID());
        users.setFirstName(user.getFirstName());
        users.setLastName(user.getLastName());
        users.setPassword(user.getPassword());

        List<Node> nodes = new ArrayList<>();
        for (Node node : user.getNodeList()) {
            Node storedNodeInstance = new Node();
            storedNodeInstance.setType(node.getType());
            storedNodeInstance.setxCoordinate(node.getxCoordinate());
            storedNodeInstance.setyCoordinate(node.getyCoordinate());
            storedNodeInstance.setNodeID(node.getNodeID());
            storedNodeInstance.setUser(node.getUser());
            nodes.add(storedNodeInstance);

        }
        users.setNodeList(nodes);
        userRepository.save(users);
    }

    @Override
    public void changeEmail(String userID, String email) {
        List<User> users = findAllUsers();
        for (User user : users) {
            if (user.getUserID().equals(userID)) {
                user.setEmail(email);
            }
        }
    }

    @Override
    public String generateNewNick(String firstName, String lastName) {
        return firstName + lastName;
    }

    @Override
    public String isPasswordCorrect(String email, String password) {
        List<User> users = findAllUsers();
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                if (user.getPassword().equals(password)) {
                    return "succeed";
                } else {
                    return "Password didn't match. Try again!";
                }
            }
        }
        return "email doesn't exists. Please register or use google authentication!";
    }

    @Override
    public String googleAuth(String email, String googleID) {
        List<User> users = findAllUsers();
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                if (user.getGoogleID().equals(googleID)) {
                    return "logged_in";
                } else {
                    return user.getGoogleID();
                }
            }
        }
        return "not_existing";
    }

    @Override
    public String generateUserID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}