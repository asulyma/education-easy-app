package com.global.education.controller;

import com.global.education.controller.dto.Course;
import com.global.education.controller.dto.SpecificationRequest;
import com.global.education.controller.handler.BaseHandler;
import com.global.education.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.validation.Valid;

import static com.global.education.mapper.CourseMapper.INSTANCE;
import static com.global.education.util.ProjectUtils.ID_REGEXP;
import static com.global.education.util.UserUtils.currentUser;

@RestController
@RequestMapping(path = "/course")
public class CourseController extends BaseHandler {

    @Autowired
    private CourseService courseService;

    @GetMapping("/{id:" + ID_REGEXP + "}")
    public Course getCourse(@PathVariable(name = "id") Long id) {
        return INSTANCE.buildCourse(courseService.getCourseById(id, currentUser()));
    }

    @GetMapping
    public List<Course> getCourses(@Valid @ModelAttribute SpecificationRequest request) {
        return INSTANCE.buildCourses(courseService.findCourses(request));
    }

    @PostMapping("/{id:" + ID_REGEXP + "}")
    public ResponseEntity<HttpStatus> startCourse(@PathVariable("id") Long courseId) {
        courseService.startCourse(courseId, currentUser());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping
    @Secured("ROLE_ADMIN")
    public ResponseEntity<HttpStatus> createCourse(@Valid @RequestBody Course course) {
        courseService.createCourse(INSTANCE.buildEntity(course));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id:" + ID_REGEXP + "}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<HttpStatus> updateCourse(@PathVariable("id") Long id, @Valid @RequestBody Course course) {
        courseService.updateCourse(id, course);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id:" + ID_REGEXP + "}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<HttpStatus> removeCourse(@PathVariable("id") Long id) {
        courseService.removeCourse(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

}
