package com.global.education.repository;

import com.global.education.model.user.Role;
import com.global.education.model.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The class for working with DataBases. Only for {@link UserEntity} entity.
 * @author Aleksandr Sulyma
 * @version 1.0
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByLogin(String login);

    List<UserEntity> findByRoleContaining(Role role);

}
