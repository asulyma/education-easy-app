package com.global.shop.repository;

import com.global.shop.model.learning.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The class for working with DataBases. Only for {@link CourseEntity} entity.
 * @author Aleksandr Sulyma
 * @version 1.0
 */
@Repository
public interface CourseRepository extends JpaRepository<CourseEntity, Long> {

}
