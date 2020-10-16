package com.global.education.service;

import static com.global.education.utils.UserUtils.currentUserName;
import static com.global.education.utils.UserUtils.currentUserUuid;
import static java.lang.String.format;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.global.education.controller.dto.Comment;
import com.global.education.controller.handler.exception.NotAllowedRuntimeException;
import com.global.education.model.learning.CommentEntity;
import com.global.education.repository.CommentRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
@RequiredArgsConstructor
public class CommentService {

	private static final String USER_CREATED_COMMENT = "User with uuid %s created a comment";
	private static final String USER_REMOVED_COMMENT = "Comment with id %s has been removed by %s user";
	private static final String COMMENT_NOT_FOUND = "There is no any comments with id %s for user %s";

	private final LessonService lessonService;
	private final CommentRepository repository;

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
		return ResponseEntity.ok(format(USER_CREATED_COMMENT, authorUuid));
	}

	@Transactional
	public ResponseEntity<String> removeComment(Long id) {
		UUID userUuid = currentUserUuid();
		CommentEntity comment = repository.findByAuthorUuidAndId(userUuid, id);
		if (comment == null) {
			throw new NotAllowedRuntimeException(format(COMMENT_NOT_FOUND, id, userUuid));
		}
		repository.delete(comment);
		return ResponseEntity.ok(format(USER_REMOVED_COMMENT, id, userUuid));

	}

}