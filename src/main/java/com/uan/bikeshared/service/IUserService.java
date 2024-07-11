package com.uan.bikeshared.service;

import com.uan.bikeshared.models.UserModel;
import java.util.List;

public interface IUserService {
    void AddUser(UserModel user);

    UserModel getUserById(long userId);

    List<UserModel> getAllUsers(); 

    void updateUser(UserModel user);

    void deleteUser(long userId);
}
