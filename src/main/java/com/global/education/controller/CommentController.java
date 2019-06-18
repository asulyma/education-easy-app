package com.global.education.controller;

import com.global.education.controller.dto.CommentResponse;
import com.global.education.controller.response.BaseResponse;
import com.global.education.model.user.UserEntity;
import com.global.education.service.CommentService;
import com.global.education.util.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

import static com.global.education.mapper.CommentMapper.INSTANCE;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserUtils userUtils;

    @GetMapping
    public BaseResponse<List<CommentResponse>> getComments(@RequestParam("lessonId") Long lessonId){
        return new BaseResponse<>(INSTANCE.buildComments(commentService.getComments(lessonId)));
    }

    @PostMapping
    public BaseResponse<CommentResponse> createComment(Principal principal,
            @RequestBody CommentResponse comment) {
        UserEntity userInfo = userUtils.getUserInfo(principal);
        return new BaseResponse<>(INSTANCE.buildComment(commentService.createComment(userInfo.getId(), comment)));
    }

}
