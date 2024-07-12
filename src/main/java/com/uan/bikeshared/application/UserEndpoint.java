package com.uan.bikeshared.application;

import com.uan.bikeshared.models.UserModel;
import com.uan.bikeshared.serviceImpl.UserService;
import com.uan.bikeshared.interfaces.CreateUserRequest;
import com.uan.bikeshared.interfaces.CreateUserResponse;
import com.uan.bikeshared.interfaces.GetAllUsersRequest;
import com.uan.bikeshared.interfaces.GetAllUsersResponse;
import com.uan.bikeshared.interfaces.GetSaldoRequest;
import com.uan.bikeshared.interfaces.GetSaldoResponse;
import com.uan.bikeshared.interfaces.ServiceStatus;
import com.uan.bikeshared.interfaces.UserXSD;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import java.util.List;

@Endpoint
public class UserEndpoint {
    private static final String NAMESPACE_URI = "http://interfaces.bikeshared.uan.com";

    @Autowired
    private UserService userService;


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "createUserRequest")
    @ResponsePayload
    public CreateUserResponse addUser(@RequestPayload CreateUserRequest request) {
        CreateUserResponse response = new CreateUserResponse();
        ServiceStatus serviceStatus = new ServiceStatus();

        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(request.getUserInfo(), userModel);
        userService.AddUser(userModel);
        serviceStatus.setStatus("SUCCESS");
        serviceStatus.setMensagem("Adicionado com sucesso!");
        response.setServiceStatus(serviceStatus);
        response.setUserInfo(request.getUserInfo());
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllUsersRequest")
    @ResponsePayload
    public GetAllUsersResponse getAllUsers(@RequestPayload GetAllUsersRequest request) {
        GetAllUsersResponse response = new GetAllUsersResponse();
        List<UserModel> users = userService.getAllUsers();
        List<UserXSD> userXSDList = response.getUsers();

        for (UserModel user : users) {
            UserXSD userXSD = new UserXSD();
            BeanUtils.copyProperties(user, userXSD);
            userXSDList.add(userXSD);
        }

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getSaldoRequest")
    @ResponsePayload
    public GetSaldoResponse getSaldo(@RequestPayload GetSaldoRequest request) {
        GetSaldoResponse response = new GetSaldoResponse();

        UserModel user = userService.getUserById(request.getUserId());
        
        if (user != null) {
            response.setSaldo(user.getSaldo());
        } else {
            response.setSaldo(0);
        }

        return response;
    }

}
