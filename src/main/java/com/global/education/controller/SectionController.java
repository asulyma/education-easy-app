package com.global.education.controller;

import com.global.education.controller.response.BaseController;
import com.global.education.controller.response.BaseResponse;
import com.global.education.model.wrapper.SectionResponse;
import com.global.education.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.global.education.mapper.SectionMapper.INSTANCE;

@RestController
@RequestMapping("/{course}/section")
public class SectionController extends BaseController {

    @Autowired
    private SectionService sectionService;

    @GetMapping
    public BaseResponse<List<SectionResponse>> getSections(@PathVariable(name = "course") String name) {
        return new BaseResponse<>(INSTANCE.buildSections(sectionService.getSections(name)));
    }

    @GetMapping("/{sectionId}")
    public BaseResponse<SectionResponse> getSectionBySectionId(@PathVariable(name = "course") String courseName,
            @PathVariable(name = "sectionId") Long id) {
        return new BaseResponse<>(INSTANCE.buildSection(sectionService.getSectionByCourseAndId(courseName, id)));
    }

}
