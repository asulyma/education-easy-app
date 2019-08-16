package com.global.education.controller;

import com.global.education.controller.dto.Course;
import com.global.education.controller.dto.SpecificationRequest;
import com.global.education.controller.dto.User;
import com.global.education.controller.handler.BaseHandler;
import com.global.education.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import static com.global.education.mapper.CourseMapper.INSTANCE;
import static com.global.education.util.Constants.ID_REGEXP;
import static com.global.education.util.UserUtils.currentUser;

@RestController
@RequestMapping(path = "/course")
public class CourseController extends BaseHandler {

    @Autowired
    private CourseService courseService;

    @GetMapping("/{id:" + ID_REGEXP + "}")
    public Course getCourse(Principal principal, @PathVariable(name = "id") Long id) {
        User user = currentUser(principal);
        return INSTANCE.buildCourse(courseService.getCourseById(id, user));
    }

    @GetMapping
    public List<Course> getCourses() {
        return INSTANCE.buildCourses(courseService.getCourses());
    }

    @GetMapping(path = "/search")
    @Secured("ROLE_ADMIN")
    public List<Course> getCourses(@Valid SpecificationRequest request) {   //todo modelAttribute
        return INSTANCE.buildCourses(courseService.findCourses(request));

    }

}
