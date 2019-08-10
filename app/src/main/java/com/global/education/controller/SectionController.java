package com.global.education.controller;

import com.global.education.controller.dto.Section;
import com.global.education.controller.handler.BaseHandler;
import com.global.education.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.global.education.mapper.SectionMapper.INSTANCE;

@RestController
@RequestMapping(path = "/section")
public class SectionController extends BaseHandler {

    @Autowired
    private SectionService sectionService;

    @GetMapping
    public List<Section> getSections(@RequestParam(name = "course") String courseName) {
        return INSTANCE.buildSections(sectionService.getSections(courseName));
    }

    @GetMapping("/{sectionId}")
    public Section getSection(@RequestParam(name = "course") String courseTitle,
            @PathVariable(name = "sectionId") Long sectionId) {
        return INSTANCE.buildSection(sectionService.getSectionByCourseAndId(courseTitle, sectionId));
    }

}
