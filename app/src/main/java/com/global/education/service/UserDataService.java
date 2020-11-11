package com.global.education.service;

import static com.global.education.mapper.SpecificationMapper.INSTANCE;
import static com.global.education.utils.UserUtils.*;
import static java.lang.String.format;

import java.util.*;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.education.common.dto.event.UserFinishLessonEvent;
import com.education.common.dto.event.UserToCourseEvent;
import com.education.common.model.Progress;
import com.education.common.model.Rank;
import com.global.education.controller.dto.SpecificationRequest;
import com.global.education.controller.dto.User;
import com.global.education.controller.handler.exception.NotAllowedRuntimeException;
import com.global.education.controller.handler.exception.NotRegisteredRuntimeException;
import com.global.education.model.UserDataEntity;
import com.global.education.repository.UserDataRepository;
import com.global.education.service.specification.SpecificationCriteria;
import com.global.education.service.specification.UserSpecificationFactory;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
@RequiredArgsConstructor
public class UserDataService {

	private static final String USER_START_COURSE = "Successfully start course %s for user: %s";
	private static final String USER_FINISH_COURSE = "Successfully finish course %s for user: %s";
	private static final String USER_ADD_COEFFICIENT = "Successfully add %s coefficient to %s course id";
	private static final String CLIENT_FOUND = "Current user can't make such operations, potentially it's client";
	private static final String USER_NOT_REGISTERED = "User with UUID: %s is not registered!";

	private final UserDataRepository userDataRepository;
	private final UserSpecificationFactory userSpecificationFactory;

	@Transactional
	public void startCourse(UserToCourseEvent event) {
		UserDataEntity user = findUser(event.getUserUuid());
		Map<Long, Progress> progressMap = user.getProgressMap();
		progressMap.put(event.getCourseId(), new Progress(TOTAL_PROGRESS));
		log.info(format(USER_START_COURSE, event.getCourseId(), event.getUserUuid()));
	}

	@Transactional
	public void finishLesson(UserFinishLessonEvent event) {
		UserDataEntity user = findUser(event.getUserUuid());
		Map<Long, Progress> progressMap = user.getProgressMap();

		Progress progress = progressMap.get(event.getCourseId());
		progress.getAlreadyDoneLessons().add(event.getAlreadyDoneLesson());
		progress.setProgressValue(progress.getProgressValue() + event.getCoefficientToProgress());

		progressMap.put(event.getCourseId(), progress);
		log.info(format(USER_ADD_COEFFICIENT, event.getCoefficientToProgress(), event.getCourseId()));
	}

	@Transactional
	public void finishCourse(UserToCourseEvent event) {
		UserDataEntity user = findUser(event.getUserUuid());
		Progress progress = user.getProgressMap().get(event.getCourseId());
		progress.setFinish(true);
		progress.setPassedDate(DateFormatUtils.format(new Date(), "yyyy-MM-dd"));
		log.info(format(USER_FINISH_COURSE, event.getCourseId(), event.getUserUuid()));
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

		log.info("User {} has been updated", userUuid);
	}

	public UserDataEntity findUser(UUID userUuid) {
		if (userUuid == null) {
			throw new NotAllowedRuntimeException(CLIENT_FOUND);
		}
		UserDataEntity userData = userDataRepository.findByUuid(userUuid);
		if (userData == null) {
			throw new NotRegisteredRuntimeException(format(USER_NOT_REGISTERED, userUuid));
		}
		return userData;
	}

	public UserDataEntity findCurrentUser() {
		UUID currentUser = currentUserUuid();
		return findUser(currentUser);
	}

	public List<UserDataEntity> findAllUsers(List<UUID> uuids) {
		return userDataRepository.findAllByUuidIn(uuids);
	}

	public List<UserDataEntity> findAllUsers(SpecificationRequest request) {
		SpecificationCriteria criteria = INSTANCE.buildSpecificationCriteria(request);
		Specification<UserDataEntity> specification = userSpecificationFactory.build(criteria);
		return userDataRepository.findAll(specification);
	}

}
