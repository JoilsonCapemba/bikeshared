package com.uan.bikeshared.service;

import java.util.List;

import com.uan.bikeshared.model.UserModel;

public interface IUserService {
    void AddUser(UserModel user);

    UserModel getUserById(long userId);

    List<UserModel> getAllUsers(); 

    void updateUser(UserModel user);

    void deleteUser(long userId);

    UserModel loginUser(String telephone, String password);

    boolean sendPoints(String telephoneFrom, String telephoneReceiver, int saldo, String userName);
    
    boolean verifyUser(String wifiCodig);
}
