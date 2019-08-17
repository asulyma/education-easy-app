package com.global.auth.service;

import com.global.auth.kafka.consumer.UserUpdateEventDto;
import com.global.auth.model.Progress;
import com.global.auth.model.UserEntity;
import com.global.auth.model.UserProvider;
import com.global.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void finishLesson(UserUpdateEventDto dto) {
        UserEntity user = userRepository.findById(dto.getUserId()).orElseThrow(NullPointerException::new);
        if (!user.getProgressMap().containsKey(dto.getCourseId())) {
            return;
        }
        Map<Long, Progress> progressMap = user.getProgressMap();

        Progress progress = progressMap.get(dto.getCourseId());
        progress.getAlreadyDoneLessons().add(dto.getAlreadyDoneLesson());
        progress.setProgressValue(progress.getProgressValue() + dto.getCoefficientToProgress());

        progressMap.put(dto.getCourseId(), progress);
        user.setProgressMap(progressMap);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        UserProvider userProvider = new UserProvider(user.getUsername(), user.getPassword(), user.getRoles());
        userProvider.setUserEntity(user);
        return userProvider;
    }

}
