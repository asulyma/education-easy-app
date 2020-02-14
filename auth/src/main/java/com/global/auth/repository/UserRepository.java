package com.global.auth.repository;

import com.global.auth.model.UserEntity;
import com.global.auth.repository.projection.UserUuid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByUsername(String username);

    UserUuid findUuidByUsername(String username);

}
