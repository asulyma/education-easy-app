package com.global.shop.repository;

import com.global.shop.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The class for working with DataBases.
 * Only for {@link User} entity.
 *
 * @author Aleksandr Sulyma
 * @version 1.0
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByLogin(String login);

    User findByRolesContaining(String role);

}
