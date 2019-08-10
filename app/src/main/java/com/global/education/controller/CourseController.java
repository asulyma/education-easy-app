package com.global.education.controller;

import com.global.education.controller.dto.Course;
import com.global.education.controller.handler.BaseHandler;
import com.global.education.model.user.UserEntity;
import com.global.education.service.CourseService;
import com.global.education.util.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

import static com.global.education.mapper.CourseMapper.INSTANCE;
import static com.global.education.util.Constants.ID_REGEXP;

@RestController
@RequestMapping(path = "/course")
public class CourseController extends BaseHandler {

    @Autowired
    private CourseService courseService;

    @Autowired
    private UserUtils userUtils;

    @GetMapping
    public List<Course> getCourses() {
        return INSTANCE.buildCourses(courseService.getCourses());
    }

    @GetMapping("/{id:" + ID_REGEXP + "}")
    public Course getCourse(Principal principal, @PathVariable(name = "id") Long id) {
        UserEntity userInfo = userUtils.getUserInfo(principal);
        return INSTANCE.buildCourse(courseService.getCourseById(id, userInfo));
    }

}
