package com.global.education.controller;

import com.global.education.controller.dto.Comment;
import com.global.education.service.CommentService;
import com.global.education.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.validation.Valid;

import static com.global.education.mapper.CommentMapper.INSTANCE;
import static com.global.education.util.UserUtils.currentUserUuid;

@RestController
@RequestMapping(path = "/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private ValidationService validationService;

    @GetMapping
    public List<Comment> getComments(@RequestParam("lessonId") Long lessonId) {
        return INSTANCE.buildComments(commentService.getComments(lessonId));
    }

    @PostMapping
    public ResponseEntity<String> createComment(@RequestBody @Valid Comment comment) {
        validationService.checkUserOnAllowGetCourse(comment.getCourseId());
        return commentService.createComment(currentUserUuid(), comment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeComment(@PathVariable("id") Long id) {
        return commentService.removeComment(id);
    }

}
