package com.global.shop.controller;

import com.global.shop.controller.response.BaseController;
import com.global.shop.controller.response.BaseResponse;
import com.global.shop.mapper.CourseMapper;
import com.global.shop.model.user.UserEntity;
import com.global.shop.model.wrapper.CourseResponse;
import com.global.shop.model.wrapper.NotificationDTO;
import com.global.shop.service.CourseService;
import com.global.shop.service.NotificationService;
import com.global.shop.util.ProjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

/**
 * @author Aleksandr Sulyma
 * @version 1.0
 */
@RestController
@RequestMapping(value = "/courses")
public class CourseController extends BaseController {

    private final CourseService courseService;
    private final NotificationService notificationService;

    private final ProjectUtils projectUtils;
    private final CourseMapper mapper;

    @Autowired
    public CourseController(CourseService courseService,
            NotificationService notificationService,
            ProjectUtils projectUtils,
            CourseMapper mapper) {
        this.courseService = courseService;
        this.notificationService = notificationService;
        this.projectUtils = projectUtils;
        this.mapper = mapper;
    }

    @GetMapping
    @Secured("ROLE_user")
    public BaseResponse<List<CourseResponse>> getListOfCourses() {
        return new BaseResponse<>(mapper.buildCourses(courseService.getListOfCourse()));
    }

    @GetMapping("/{id}")
    @Secured("ROLE_user")
    public BaseResponse<CourseResponse> getCourseById(Principal principal, @PathVariable(name = "id") Long id) {
        UserEntity userEntityInfo = projectUtils.getUserInfo(principal);
        return new BaseResponse<>(mapper.buildCourse(courseService.getCourseById(id, userEntityInfo)));
    }

    @PostMapping("/allowCourse")
    @Secured("ROLE_user")
    public BaseResponse sendPermissionRequestOnCourse(@RequestBody NotificationDTO dto) {
        notificationService.requestToAllowCourse(dto);
        return new BaseResponse();
    }

}
