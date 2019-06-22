package com.global.education.repository;

import com.global.education.model.user.Role;
import com.global.education.model.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The class for working with DataBases. Only for {@link UserEntity} entity.
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>, JpaSpecificationExecutor<UserEntity> {

    UserEntity findByLogin(String login);

    List<UserEntity> findByRole(Role role);

}
