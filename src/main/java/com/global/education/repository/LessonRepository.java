package com.global.education.repository;

import com.global.education.model.learning.LessonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The class for working with DataBases. Only for {@link LessonEntity} entity.
 * @author Aleksandr Sulyma
 * @version 1.0
 */
@Repository
public interface LessonRepository extends JpaRepository<LessonEntity, Long> {

    List<LessonEntity> findAllBySectionCourseNameAndSectionId(String courseName, Long sectionId);

    LessonEntity findBySectionCourseNameAndSectionIdAndId(String courseName, Long sectionId, Long lessonId);

    Long countAllBySectionCourseName(String courseName);

}
