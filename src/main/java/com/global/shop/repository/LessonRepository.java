package com.global.shop.repository;

import com.global.shop.model.learning.LessonEntity;
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

    List<LessonEntity> findAllBySectionCourseNameAndSectionId(String nameOfCourse, Long sectionId);

    LessonEntity findBySectionCourseNameAndSectionIdAndId(String nameOfCourse, Long sectionId, Long lessonId);

    Long countAllBySectionCourseName(String nameOfCourse);

}
