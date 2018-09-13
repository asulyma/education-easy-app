package com.global.shop.repository;

import com.global.shop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Tim Hlovatskyi
 * @version 1.0
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAll();

}
