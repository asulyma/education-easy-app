package com.global.auth.service;

import com.education.common.kafka.dto.UserFinishLessonEvent;
import com.education.common.kafka.dto.UserStartCourseEvent;
import com.education.common.model.Progress;
import com.education.common.model.Rank;
import com.global.auth.model.UserEntity;
import com.global.auth.model.UserProvider;
import com.global.auth.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void finishLesson(UserFinishLessonEvent dto) {
        UserEntity user = findUser(dto.getUserId());
        Map<Long, Progress> progressMap = user.getProgressMap();

        Progress progress = progressMap.get(dto.getCourseId());
        progress.getAlreadyDoneLessons().add(dto.getAlreadyDoneLesson());
        progress.setProgressValue(progress.getProgressValue() + dto.getCoefficientToProgress());

        progressMap.put(dto.getCourseId(), progress);
        user.setProgressMap(progressMap);
        log.info("Successfully add {} coefficient to {} course id", dto.getCoefficientToProgress(), dto.getCourseId());
    }

    @Transactional
    public void startCourse(UserStartCourseEvent dto) {
        UserEntity user = findUser(dto.getUserId());
        Map<Long, Progress> progressMap = user.getProgressMap();
        progressMap.put(dto.getCourseId(), new Progress());
        log.info("Successfully start course {}", dto.getCourseId());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        String[] rolesSize = new String[user.getRoles().size()];
        List<GrantedAuthority> authorityList = AuthorityUtils.createAuthorityList(user.getRoles().toArray(rolesSize));
        return new UserProvider(user.getUsername(), user.getPassword(), authorityList)
                .setUserEntity(user);
    }

    @Transactional
    public UserEntity createUser() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("john");
        userEntity.setPassword(new BCryptPasswordEncoder().encode("john"));
        userEntity.setRank(Rank.TRAINEE);
        userEntity.setEmail("john@email.com");
        userEntity.setRoles(Collections.singleton("ROLE_USER"));
        userRepository.save(userEntity);
        return userEntity;
    }

    private UserEntity findUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(IllegalArgumentException::new);
    }

}
