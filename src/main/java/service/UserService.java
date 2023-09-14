package service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.UserRepository;
import entity.User;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(User user){
        return userRepository.save(user);
    }

    public List<User> createUsers(List<User> users){
        return userRepository.saveAll(users);
    }

    public User getUserById(int id){
        return userRepository.findById(id).orElse(null);
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public User updatUser(User user){
        Optional<User> optionalUser = userRepository.findById(user.getId());
        if(optionalUser.isPresent()){
            User oldUser = optionalUser.get();
            oldUser.setName(user.getName());
            oldUser.setAddress(user.getAddress());
            return userRepository.save(oldUser);
        }
        else return null;
    }

    public String deleteUserById(int id){
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()){
            userRepository.deleteById(id);
            return "The user was deleted successfully";
        }
        else return "User id not found";
    }
}
