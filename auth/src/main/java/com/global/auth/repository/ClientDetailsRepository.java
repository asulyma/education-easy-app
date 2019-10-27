package com.global.auth.repository;

import com.global.auth.model.ClientDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientDetailsRepository extends JpaRepository<ClientDetailsEntity, Long> {

    ClientDetailsEntity findByClientId(String clientId);

    void deleteByClientId(String clientId);

}
