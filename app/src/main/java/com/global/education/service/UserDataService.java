package com.global.education.service;

import static com.global.education.utils.UserUtils.currentUserName;
import static com.global.education.utils.UserUtils.currentUserUuid;

import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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


@Slf4j
@Service
public class UserDataService {

	@Autowired
	private UserDataRepository userDataRepository;

	@Transactional
	public void startCourse(UserStartCourseEvent event) {
		UserDataEntity user = findUser(event.getUserUuid());
		Map<Long, Progress> progressMap = user.getProgressMap();
		progressMap.put(event.getCourseId(), new Progress());
		log.info("Successfully start course {} for user: {}", event.getCourseId(), event.getUserUuid());
	}

	@Transactional
	public void finishLesson(UserFinishLessonEvent event) {
		UserDataEntity user = findUser(event.getUserUuid());
		Map<Long, Progress> progressMap = user.getProgressMap();

		Progress progress = progressMap.get(event.getCourseId());
		progress.getAlreadyDoneLessons().add(event.getAlreadyDoneLesson());
		progress.setProgressValue(progress.getProgressValue() + event.getCoefficientToProgress());

		progressMap.put(event.getCourseId(), progress);
		log.info("Successfully add {} coefficient to {} course id", event.getCoefficientToProgress(), event.getCourseId());
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

	public UserDataEntity findCurrentUser() {
		UUID currentUser = currentUserUuid();
		return findUser(currentUser);
	}

}
