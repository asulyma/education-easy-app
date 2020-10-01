package com.global.education.service;

import com.global.education.controller.dto.Comment;
import com.global.education.controller.handler.exception.NotAllowedRuntimeException;
import com.global.education.model.learning.CommentEntity;
import com.global.education.repository.CommentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static com.global.education.utils.UserUtils.currentUserUuid;

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
    public ResponseEntity<String> createComment(UUID authorUuid, Comment comment) {
        CommentEntity entity = new CommentEntity()
                .setAuthorUuid(authorUuid)
                .setContent(comment.getContent())
                .setLesson(lessonService.getLessonById(comment.getLessonId(), comment.getCourseId()));

        repository.save(entity);
        return new ResponseEntity<>("User with id: " + authorUuid + " created a comment.", HttpStatus.CREATED);
    }

    @Transactional
    public ResponseEntity<String> removeComment(Long id) {
        UUID userUuid = currentUserUuid();
        CommentEntity comment = repository.findByAuthorUuidAndId(userUuid, id);
        if (comment == null) {
            throw new NotAllowedRuntimeException("There is no any comments with id " + id + " for user " + userUuid);
        }
        repository.delete(comment);
        return new ResponseEntity<>("Comment with id " + id + " has been removed", HttpStatus.CREATED);

    }

}