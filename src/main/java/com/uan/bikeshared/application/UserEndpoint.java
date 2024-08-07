package com.uan.bikeshared.application;

import com.uan.bikeshared.serviceimpl.UserService;
import com.uan.interfaces.*;
import com.uan.bikeshared.model.UserModel;
import com.uan.bikeshared.exceptions.AuthenticationException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import java.util.List;

@Endpoint
public class UserEndpoint {
    private static final String NAMESPACE_URI = "http://interfaces.uan.com";

    @Autowired
    private UserService userService;


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "createUserRequest")
    @ResponsePayload
    public CreateUserResponse addUser(@RequestPayload CreateUserRequest request) {
        CreateUserResponse response = new CreateUserResponse();
        ServiceStatus serviceStatus = new ServiceStatus();

        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(request.getUserInfo(), userModel);
        
        // Hash the password before saving
        userModel.setPassword(userService.hashPassword(userModel.getPassword()));
        
        if(userModel.getType() == 1 ){
            userModel.setSaldo(10);
        }else{
            userModel.setSaldo(0);
        }
        
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

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "loginRequest")
    @ResponsePayload
    public LoginResponse login(@RequestPayload LoginRequest request) {
        LoginResponse response = new LoginResponse();
        try {
            UserModel user = userService.loginUser(request.getTelephone(), request.getPassword());
            
            if (user != null) {
                response.setName(user.getName());
                response.setEmail(user.getEmail());
                response.setTelephone(user.getTelephone());
                response.setSaldo(user.getSaldo());
                response.setId(user.getId());
                response.setToken(userService.generateToken(user));
                response.setType(user.getType());
            } else {
                throw new AuthenticationException("Credenciais inválidas");
            }
        } catch (AuthenticationException e) {
            ServiceStatus serviceStatus = new ServiceStatus();
            serviceStatus.setStatus("FAILURE");
            serviceStatus.setMensagem(e.getMessage());
            // response.setServiceStatus(serviceStatus);
        }

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "sendPointRequest")
    @ResponsePayload
    public SendPointResponse sendPoint(@RequestPayload SendPointRequest request) {
        SendPointResponse response = new SendPointResponse();
        ServiceStatus serviceStatus = new ServiceStatus();

        try {
            boolean success = userService.sendPoints(
                request.getTelephoneFrom(),
                request.getTelephoneReceiver(),
                request.getSaldo()
            );

            if (success) {
                serviceStatus.setStatus("SUCCESS");
                serviceStatus.setMensagem("Pontos enviados com sucesso");
            } else {
                serviceStatus.setStatus("FAILURE");
                serviceStatus.setMensagem("Falha ao enviar pontos");
            }
        } catch (RuntimeException e) {
            serviceStatus.setStatus("FAILURE");
            serviceStatus.setMensagem(e.getMessage());
        }
        response.setServiceStatus(serviceStatus);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "verifyUserRequest")
    @ResponsePayload
    public VerifyUserResponse verifyUser(@RequestPayload VerifyUserRequest request) {
        VerifyUserResponse response = new VerifyUserResponse();
        
        boolean exists = userService.verifyUser(request.getWifiCodig());
        response.setExist(exists);

        return response;
    }

}