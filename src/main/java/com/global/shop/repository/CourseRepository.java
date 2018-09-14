package com.global.shop.repository;

import com.global.shop.model.learning.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @version 1.0
 */
@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findAll();

}
