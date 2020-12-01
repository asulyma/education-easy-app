package com.global.education.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.global.education.model.UserDataEntity;


@Repository
public interface UserDataRepository
		extends JpaRepository<UserDataEntity, Long>, JpaSpecificationExecutor<UserDataEntity> {

	UserDataEntity findByUuid(UUID uuid);

	List<UserDataEntity> findAllByUuidIn(List<UUID> uuids);

}
