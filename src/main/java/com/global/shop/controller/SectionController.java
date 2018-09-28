package com.global.shop.controller;

import com.global.shop.controller.response.BaseController;
import com.global.shop.controller.response.BaseResponse;
import com.global.shop.model.learning.Section;
import com.global.shop.model.wrapper.SectionWrapper;
import com.global.shop.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Aleksandr Sulyma
 * @version 1.0
 */
@RestController
@RequestMapping("/{course}/sections")
public class SectionController extends BaseController {

    private final SectionService sectionService;

    @Autowired
    public SectionController(SectionService sectionService) {
        this.sectionService = sectionService;
    }


    @GetMapping
    @Secured("ROLE_user")
    public BaseResponse<List<SectionWrapper>> getListOfSectionsByCourseName(@PathVariable(name = "course") String name) {
        return new BaseResponse<>(sectionService.getSectionsByCourseName(name));
    }

    @GetMapping(path = "/{sectionId}")
    @Secured("ROLE_user")
    public BaseResponse<Section> getSectionByCourseAndId(@PathVariable(name = "course") String nameOfCourse,
                                                         @PathVariable(name = "sectionId") Long id) {

        return new BaseResponse<>(sectionService.getSectionByCourseAndId(nameOfCourse, id));
    }
}
