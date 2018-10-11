package com.global.shop.controller;

import com.global.shop.controller.response.BaseController;
import com.global.shop.controller.response.BaseResponse;
import com.global.shop.mapper.SectionMapper;
import com.global.shop.model.user.User;
import com.global.shop.model.wrapper.SectionViewWrapper;
import com.global.shop.model.wrapper.SectionWrapper;
import com.global.shop.service.SectionService;
import com.global.shop.util.ProjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

/**
 * @author Aleksandr Sulyma
 * @version 1.0
 */
@RestController
@RequestMapping("/{course}/sections")
public class SectionController extends BaseController {

    private final SectionService sectionService;

    private final ProjectUtils projectUtils;

    private final SectionMapper sectionMapper;

    @Autowired
    public SectionController(SectionService sectionService,
                             ProjectUtils projectUtils,
                             SectionMapper sectionMapper) {
        this.sectionService = sectionService;
        this.projectUtils = projectUtils;
        this.sectionMapper = sectionMapper;
    }


    @GetMapping
    @Secured("ROLE_user")
    public BaseResponse<List<SectionWrapper>> getListOfSectionsByCourseName(@PathVariable(name = "course") String name) {
        return new BaseResponse<>(sectionMapper.sectionsToListOfWrapper(sectionService.getSectionsByCourseName(name)));
    }


    @GetMapping(path = "/{sectionId}")
    @Secured("ROLE_user")
    public BaseResponse<SectionViewWrapper> getSectionByCourseAndId(Principal principal,
                                                                    @PathVariable(name = "course") String nameOfCourse,
                                                                    @PathVariable(name = "sectionId") Long id) {

        User userInfo = projectUtils.getUserInfo(principal);
        return new BaseResponse<>(sectionMapper.sectionToViewWrapper(
                sectionService.getSectionByCourseAndId(nameOfCourse, id, userInfo)));
    }


    @PutMapping("/{sectionId}")
    @Secured("ROLE_user")
    public BaseResponse startSection(@PathVariable(name = "sectionId") Long sectionId,
                                     @RequestParam(name = "userId") Long userId) {

        sectionService.startSection(sectionId, userId);
        return new BaseResponse();
    }
}
