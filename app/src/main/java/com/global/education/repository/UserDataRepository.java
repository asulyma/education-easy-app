package com.global.education.repository;

import com.global.education.model.UserDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserDataRepository extends JpaRepository<UserDataEntity, Long> {

    UserDataEntity findByUuid(UUID uuid);

}
