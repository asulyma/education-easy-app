package com.global.education.controller;

import com.global.education.controller.dto.Course;
import com.global.education.controller.dto.SpecificationRequest;
import com.global.education.controller.handler.BaseHandler;
import com.global.education.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import static com.global.education.util.Constants.ID_REGEXP;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/course", produces = APPLICATION_JSON_VALUE)
public class CourseController extends BaseHandler {

    @Autowired
    private CourseService courseService;

    @GetMapping("/{id:" + ID_REGEXP + "}")
    public Course getCourse(@PathVariable(name = "id") Long id) {
        return INSTANCE.buildCourse(courseService.getCourseById(id));
    }

    @GetMapping
    public List<Course> getCourses() {
        return INSTANCE.buildCourses(courseService.getCourses());
    }

    @GetMapping("/search")
    //@Secured("ROLE_ADMIN")
    public List<Course> getCourses(@Valid @ModelAttribute SpecificationRequest request) {
        return INSTANCE.buildCourses(courseService.findCourses(request));
    }

    @PostMapping
    public ResponseEntity<HttpStatus> createCourse(@Valid @RequestBody Course course) {
        courseService.createCourse(INSTANCE.buildEntity(course));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id: " + ID_REGEXP + "}")
    public ResponseEntity<HttpStatus> updateCourse(@PathVariable("id") Long id, @Valid @RequestBody Course course) {
        courseService.updateCourse(id, course);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
