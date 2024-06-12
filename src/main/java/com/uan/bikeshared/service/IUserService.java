package com.uan.bikeshared.service;

import com.uan.bikeshared.models.UserModel;

public interface IUserService {
    void AddUser(UserModel user);

    UserModel getUserById(long userId);

    void updateUser(UserModel user);

    void deleteUser(long userId);
}
