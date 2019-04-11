package com.global.shop.repository;

import com.global.shop.model.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The class for working with DataBases. Only for {@link UserEntity} entity.
 * @author Aleksandr Sulyma
 * @version 1.0
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByLogin(String login);

    UserEntity findByRolesContaining(String role);

}
