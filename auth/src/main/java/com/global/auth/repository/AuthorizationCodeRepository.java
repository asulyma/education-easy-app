package com.global.auth.repository;

import com.global.auth.model.AuthorizationCodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorizationCodeRepository extends JpaRepository<AuthorizationCodeEntity, Long> {

    AuthorizationCodeEntity findByCode(String code);

}
