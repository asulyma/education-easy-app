package com.global.education.controller;

import static com.global.education.mapper.CommentMapper.INSTANCE;
import static com.global.education.utils.UserUtils.currentUserUuid;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.global.education.controller.dto.Comment;
import com.global.education.service.CommentService;
import com.global.education.service.ValidationService;


@RestController
@RequestMapping("/comment")
public class CommentController {

	@Autowired
	private CommentService commentService;
	@Autowired
	private ValidationService validationService;

	@GetMapping
	public List<Comment> getComments(@RequestParam Long lessonId) {
		return INSTANCE.buildComments(commentService.getComments(lessonId));
	}

	@PostMapping
	public ResponseEntity<String> createComment(@RequestBody @Valid Comment comment) {
		validationService.checkUserOnAllowGetCourse(comment.getCourseId());
		return commentService.createComment(currentUserUuid(), comment);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> removeComment(@PathVariable Long id) {
		return commentService.removeComment(id);
	}

}
