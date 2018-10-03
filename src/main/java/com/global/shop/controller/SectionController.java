package com.global.shop.controller;

import com.global.shop.controller.response.BaseController;
import com.global.shop.controller.response.BaseResponse;
import com.global.shop.model.learning.Section;
import com.global.shop.model.wrapper.NotificationWrapper;
import com.global.shop.model.wrapper.SectionWrapper;
import com.global.shop.service.NotificationService;
import com.global.shop.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Aleksandr Sulyma
 * @version 1.0
 */
@RestController
@RequestMapping("/{course}/sections")
public class SectionController extends BaseController {

    private final SectionService sectionService;
    private final NotificationService notificationService;

    @Autowired
    public SectionController(SectionService sectionService, NotificationService notificationService) {
        this.sectionService = sectionService;
        this.notificationService = notificationService;
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

    @PostMapping("/startSection")
    @Secured("ROLE_user")
    public BaseResponse startSection(@RequestBody NotificationWrapper wrapper) {
        notificationService.createInfoUserNotification(wrapper);
        sectionService.startSection(wrapper);
        return new BaseResponse();
    }
}
