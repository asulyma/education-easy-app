package com.global.education.service;

import com.education.common.kafka.dto.UserFinishLessonEvent;
import com.education.common.kafka.dto.UserStartCourseEvent;
import com.education.common.model.Progress;
import com.global.education.model.UserDataEntity;
import com.global.education.repository.UserDataRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
public class UserDataService {

    @Autowired
    private UserDataRepository userDataRepository;

    @Transactional
    public void finishLesson(UserFinishLessonEvent dto) {
        UserDataEntity user = findUser(dto.getUserUuid());
        Map<Long, Progress> progressMap = user.getProgressMap();

        Progress progress = progressMap.get(dto.getCourseId());
        if (progress != null && progress.getAlreadyDoneLessons().contains(dto.getAlreadyDoneLesson())) {
            log.info("Lesson already done.");
            return;
        }

        progress.getAlreadyDoneLessons().add(dto.getAlreadyDoneLesson());
        progress.setProgressValue(progress.getProgressValue() + dto.getCoefficientToProgress());

        progressMap.put(dto.getCourseId(), progress);
        user.setProgressMap(progressMap);
        log.info("Successfully add {} coefficient to {} course id", dto.getCoefficientToProgress(), dto.getCourseId());
    }

    @Transactional
    public void startCourse(UserStartCourseEvent dto) {
        UserDataEntity user = findUser(dto.getUserUuid());
        Map<Long, Progress> progressMap = user.getProgressMap();
        progressMap.put(dto.getCourseId(), new Progress());
        log.info("Successfully start course {}", dto.getCourseId());
    }

    public UserDataEntity findUser(UUID userUuid) {
        return userDataRepository.findByUuid(userUuid);
    }

}