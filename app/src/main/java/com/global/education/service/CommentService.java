package com.global.education.service;

import static com.global.education.utils.UserUtils.currentUserName;
import static com.global.education.utils.UserUtils.currentUserUuid;
import static java.lang.String.format;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.global.education.controller.dto.Comment;
import com.global.education.controller.handler.exception.NotAllowedRuntimeException;
import com.global.education.model.learning.CommentEntity;
import com.global.education.repository.CommentRepository;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class CommentService {

	private static final String CREATED_COMMENT = "User with uuid %s created a comment";
	private static final String REMOVED_COMMENT = "Comment with id %s has been removed by %s user";

	@Autowired
	private LessonService lessonService;
	@Autowired
	private CommentRepository repository;

	public List<CommentEntity> getComments(Long lessonId) {
		return repository.findAllByLessonId(lessonId);
	}

	@Transactional
	public ResponseEntity<String> createComment(Comment comment) {
		UUID authorUuid = currentUserUuid();
		String authorName = currentUserName();

		CommentEntity entity = new CommentEntity()
				.setAuthorUuid(authorUuid)
				.setAuthorName(authorName)
				.setContent(comment.getContent())
				.setLesson(lessonService.getLessonById(comment.getLessonId(), comment.getCourseId()));

		repository.save(entity);
		return ResponseEntity.ok(format(CREATED_COMMENT, authorUuid));
	}

	@Transactional
	public ResponseEntity<String> removeComment(Long id) {
		UUID userUuid = currentUserUuid();
		CommentEntity comment = repository.findByAuthorUuidAndId(userUuid, id);
		if (comment == null) {
			throw new NotAllowedRuntimeException("There is no any comments with id " + id + " for user " + userUuid);
		}
		repository.delete(comment);
		return ResponseEntity.ok(format(REMOVED_COMMENT, id, userUuid));

	}

}