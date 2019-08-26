package com.global.education.controller;

import com.global.education.controller.dto.Comment;
import com.global.education.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.global.education.mapper.CommentMapper.INSTANCE;
import static com.global.education.util.UserUtils.currentUserId;

@RestController
@RequestMapping(path = "/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping
    public List<Comment> getComments(@RequestParam("lessonId") Long lessonId) {
        return INSTANCE.buildComments(commentService.getComments(lessonId));
    }

    @PostMapping
    public Comment createComment(@RequestBody Comment comment) {
        return INSTANCE.buildComment(commentService.createComment(currentUserId(), comment));
    }

}
