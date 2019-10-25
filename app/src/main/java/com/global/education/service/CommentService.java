package com.global.education.service;

import com.global.education.controller.dto.Comment;
import com.global.education.model.learning.CommentEntity;
import com.global.education.repository.CommentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class CommentService {

    @Autowired
    private LessonService lessonService;

    @Autowired
    private CommentRepository repository;

    public List<CommentEntity> getComments(Long lessonId) {
        return repository.findAllByLessonId(lessonId);
    }

    @Transactional
    public void createComment(UUID authorUuid, Comment comment) {
        CommentEntity entity = new CommentEntity()
                .setAuthorUuid(authorUuid)
                .setContent(comment.getContent())
                .setLesson(lessonService.getLessonById(comment.getLessonId()));

        repository.save(entity);
        log.info("User with id: " + authorUuid + " created comment.");
    }

    public void removeComment(Long id) {
        repository.deleteById(id);
        log.info("Comment with id {} has been removed", id);
    }

}