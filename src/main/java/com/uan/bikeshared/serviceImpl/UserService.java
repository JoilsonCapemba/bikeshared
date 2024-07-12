package com.uan.bikeshared.serviceImpl;

import com.uan.bikeshared.models.UserModel;
import com.uan.bikeshared.repository.UserRepository;
import com.uan.bikeshared.service.IUserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.transaction.Transactional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Value;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    UserRepository userRepository;

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private long jwtExpiration;

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

    @Override
    public UserModel loginUser(String telephone, String password, String wifiCodig) {
        UserModel user = userRepository.findByTelephone(telephone);
        if (user != null && BCrypt.checkpw(password, user.getPassword()) && user.getMacAddress().equals(wifiCodig)) {
            return user;
        }
        return null;
    }

    public String generateToken(UserModel user) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpiration);

        return Jwts.builder()
                .setSubject(Long.toString(user.getId()))
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String hashPassword(String plainTextPassword) {
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }

    @Override
    @Transactional
    public boolean sendPoints(long userFromId, String wifiCodigUserReceiver, int saldo, String userName) {
        // Encontrar o usuário que está enviando os pontos
        UserModel senderUser = userRepository.findById(userFromId).orElse(null);
        if (senderUser == null) {
            throw new RuntimeException("Usuário remetente não encontrado");
        }

        // Verificar se o remetente tem saldo suficiente
        if (senderUser.getSaldo() < saldo) {
            throw new RuntimeException("Saldo insuficiente");
        }

        // Encontrar o usuário receptor baseado no wifiCodig (macAddress)
        UserModel receiverUser = userRepository.findByMacAddress(wifiCodigUserReceiver);
        if (receiverUser == null) {
            throw new RuntimeException("Usuário receptor não encontrado");
        }

        // Verificar se o nome do usuário receptor corresponde
        if (!receiverUser.getName().equals(userName)) {
            throw new RuntimeException("Nome do usuário receptor não corresponde");
        }

        // Transferir os pontos
        senderUser.setSaldo(senderUser.getSaldo() - saldo);
        receiverUser.setSaldo(receiverUser.getSaldo() + saldo);

        // Salvar as alterações
        userRepository.save(senderUser);
        userRepository.save(receiverUser);

        return true;
    }

    @Override
    public boolean verifyUser(String wifiCodig) {
        UserModel user = userRepository.findByMacAddress(wifiCodig);
        return user != null;
    }

}
