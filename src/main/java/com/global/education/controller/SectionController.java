package com.global.education.controller;

import com.global.education.controller.response.BaseController;
import com.global.education.controller.response.BaseResponse;
import com.global.education.mapper.SectionMapper;
import com.global.education.model.user.UserEntity;
import com.global.education.model.wrapper.SectionResponse;
import com.global.education.service.SectionService;
import com.global.education.util.ProjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/{course}/section")
public class SectionController extends BaseController {

    private final SectionService sectionService;
    private final ProjectUtils projectUtils;
    private final SectionMapper sectionMapper = SectionMapper.INSTANCE;

    @Autowired
    public SectionController(SectionService sectionService,
                             ProjectUtils projectUtils) {
        this.sectionService = sectionService;
        this.projectUtils = projectUtils;
    }

    @GetMapping
    @Secured("ROLE_user")
    public BaseResponse<List<SectionResponse>> getSectionsByCourseName(@PathVariable(name = "course") String name) {
        return new BaseResponse<>(sectionMapper.buildSections(sectionService.getSectionsByCourseName(name)));
    }

    @GetMapping("/{sectionId}")
    @Secured("ROLE_user")
    public BaseResponse<SectionResponse> getSectionBySectionId(Principal principal,
            @PathVariable(name = "course") String courseName,
            @PathVariable(name = "sectionId") Long id) {

        UserEntity userEntityInfo = projectUtils.getUserInfo(principal);
        return new BaseResponse<>(
                sectionMapper.buildSection(sectionService.getSectionByCourseAndId(courseName, id, userEntityInfo)));
    }

    @PostMapping("/{sectionId}")
    @Secured("ROLE_user")
    public BaseResponse startSection(Principal principal,
                                     @PathVariable(name = "sectionId") Long sectionId) {
        UserEntity userEntityInfo = projectUtils.getUserInfo(principal);
        sectionService.startSection(sectionId, userEntityInfo.getId());
        return new BaseResponse();
    }
}
