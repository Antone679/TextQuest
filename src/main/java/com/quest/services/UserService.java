package com.quest.services;

import com.quest.entity.User;
import com.quest.repository.UserRepository;
import com.quest.utils.HashPassword;

public class UserService {

    private UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public void saveUser(String name, String password, String email){
        String hashPassword = HashPassword.hashPassword(password);

        User userToSave = new User(name, hashPassword, email);
        repository.saveUser(userToSave);
    }

    public User getUserById(int id){
        return repository.getUser(id);
    }

    public boolean checkLogin(String name){
      return repository.checkLogin(name);
    }

    public boolean checkPassword(String password){
        String hashPassword = HashPassword.hashPassword(password);

        return repository.checkPassword(hashPassword);
    }

    public UserRepository getRepository() {
        return repository;
    }

    public void setRepository(UserRepository repository) {
        this.repository = repository;
    }
}
