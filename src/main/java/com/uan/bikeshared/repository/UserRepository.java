package com.uan.bikeshared.repository;


import com.uan.bikeshared.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel,Long> {
    UserModel findUserById(Long id);
}
