package com.global.education.service;

import com.education.common.kafka.dto.UserFinishLessonEvent;
import com.education.common.kafka.dto.UserStartCourseEvent;
import com.education.common.model.Progress;
import com.education.common.model.Rank;
import com.global.education.controller.dto.User;
import com.global.education.controller.handler.exception.NotAllowedRuntimeException;
import com.global.education.controller.handler.exception.NotRegisteredRuntimeException;
import com.global.education.model.UserDataEntity;
import com.global.education.repository.UserDataRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.UUID;

import static com.global.education.util.UserUtils.currentUserName;
import static com.global.education.util.UserUtils.currentUserUuid;

@Slf4j
@Service
public class UserDataService {

    @Autowired
    private UserDataRepository userDataRepository;

    @Transactional
    public void startCourse(UserStartCourseEvent dto) {
        UserDataEntity user = findUser(dto.getUserUuid());
        Map<Long, Progress> progressMap = user.getProgressMap();
        progressMap.put(dto.getCourseId(), new Progress());
        log.info("Successfully start course {} for user: {}", dto.getCourseId(), dto.getUserUuid());
    }

    @Transactional
    public void finishLesson(UserFinishLessonEvent dto) {
        UserDataEntity user = findUser(dto.getUserUuid());
        Map<Long, Progress> progressMap = user.getProgressMap();

        Progress progress = progressMap.get(dto.getCourseId());
        progress.getAlreadyDoneLessons().add(dto.getAlreadyDoneLesson());
        progress.setProgressValue(progress.getProgressValue() + dto.getCoefficientToProgress());

        progressMap.put(dto.getCourseId(), progress);
        log.info("Successfully add {} coefficient to {} course id", dto.getCoefficientToProgress(), dto.getCourseId());
    }

    @Transactional
    public void createOrUpdateUserData(User user) {
        UUID userUuid = currentUserUuid();
        UserDataEntity entity = userDataRepository.findByUuid(userUuid);
        if (entity == null) {
            entity = new UserDataEntity();
            entity.setUuid(userUuid);
        }
        entity.setUsername(currentUserName());
        entity.setEmail(user.getEmail());
        entity.setRank(Rank.valueOf(user.getRank()));
        userDataRepository.save(entity);
    }

    public UserDataEntity findUser(UUID userUuid) {
        if (userUuid == null) {
            throw new NotAllowedRuntimeException("Current user can't make such operations");
        }
        UserDataEntity userData = userDataRepository.findByUuid(userUuid);
        if (userData == null) {
            throw new NotRegisteredRuntimeException("User with UUID: " + userUuid + " is not registered!");
        }
        return userData;
    }

}
