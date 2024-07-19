package com.uan.bikeshared.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uan.bikeshared.model.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
    UserModel findUserById(Long id);

    UserModel findByTelephone(String telephone);

    UserModel findByMacAddress(String macAddress);
}
