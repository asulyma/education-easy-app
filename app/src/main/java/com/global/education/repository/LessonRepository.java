package com.global.education.repository;

import com.global.education.model.learning.LessonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<LessonEntity, Long> {

    LessonEntity findByIdAndCourseId(Long lessonId, Long courseId);

    List<LessonEntity> findAllByCourseId(Long courseId);

    int countAllByCourseId(Long courseId);
}
