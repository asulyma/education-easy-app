package com.global.education.service;

import com.global.education.controller.dto.Comment;
import com.global.education.model.learning.CommentEntity;
import com.global.education.repository.CommentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CommentService {

    @Autowired
    private LessonService lessonService;

    @Autowired
    private CommentRepository repository;

    public CommentEntity createComment(Long authorId, Comment comment) {
        CommentEntity entity = new CommentEntity()
                .setAuthorId(authorId)
                .setContent(comment.getContent())
                .setLesson(lessonService.getLessonById(comment.getLessonId()));

        log.info("User with id: " + authorId + " created comment.");
        return repository.save(entity);
    }

    public List<CommentEntity> getComments(Long lessonId) {
        return repository.findAllByLessonId(lessonId);
    }
}
