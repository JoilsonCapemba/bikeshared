package com.uan.bikeshared.application;

import com.uan.bikeshared.models.UserModel;
import com.uan.bikeshared.serviceImpl.UserService;
import com.uan.bikeshared.interfaces.CreateUserRequest;
import com.uan.bikeshared.interfaces.CreateUserResponse;
import com.uan.bikeshared.interfaces.ServiceStatus;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

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
        return response;
    }
}
