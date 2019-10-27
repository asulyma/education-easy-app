package com.global.education.service;

import com.education.common.model.Progress;
import com.global.education.controller.handler.exception.BadRequestParametersRuntimeException;
import com.global.education.controller.handler.exception.NotAllowedRuntimeException;
import com.global.education.model.UserDataEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.global.education.util.UserUtils.currentUserUuid;

@Slf4j
@Component
public class ValidationService {

    @Autowired
    private UserDataService userDataService;

    public static final String ID_REGEXP = "^[0-9]{1,9}";

    public void checkUserOnAllowGetCourse(Long courseId) {
        UserDataEntity user = userDataService.findUser(currentUserUuid());
        if (user.getProgressMap().containsKey(courseId)) {
            return;
        }
        throw new NotAllowedRuntimeException(
                "User " + user.getUsername() + " did not start course with id " + courseId);
    }

    public void checkUserOnFinishLesson(Long courseId, Long lessonId) {
        UserDataEntity user = userDataService.findUser(currentUserUuid());
        Progress progress = user.getProgressMap().get(courseId);
        if (progress == null) {
            throw new NotAllowedRuntimeException(
                    "User " + user.getUuid() + " didn't start the course " + courseId);
        } else if (progress.getAlreadyDoneLessons().contains(lessonId)) {
            throw new BadRequestParametersRuntimeException(
                    "User " + user.getUuid() + " already finished the lesson " + lessonId);
        }
    }

}
