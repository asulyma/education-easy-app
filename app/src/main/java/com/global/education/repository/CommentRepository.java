package com.global.education.repository;

import com.global.education.model.learning.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

    CommentEntity findByAuthorUuidAndId(UUID authorUuid, Long id);

    List<CommentEntity> findAllByLessonId(Long lessonId);

}
