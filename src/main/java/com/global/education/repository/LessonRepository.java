package com.global.education.repository;

import com.global.education.model.learning.LessonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<LessonEntity, Long> {

    List<LessonEntity> findAllBySectionCourseNameAndSectionId(String courseName, Long sectionId);

    Long countAllBySectionCourseName(String courseName);

}
