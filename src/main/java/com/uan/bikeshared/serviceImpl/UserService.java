package com.uan.bikeshared.serviceImpl;

import com.uan.bikeshared.models.UserModel;
import com.uan.bikeshared.repository.UserRepository;
import com.uan.bikeshared.service.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public void AddUser(UserModel user) {
        userRepository.save(user);
    }

    @Override
    public UserModel getUserById(long userId) {
        UserModel user = userRepository.findById(userId).orElse(null);
        return user;
    }

    @Override
    public List<UserModel> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void updateUser(UserModel user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUser(long userId) {
        userRepository.deleteById(userId);
    }
}
