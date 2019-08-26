package com.global.auth.service;

import com.education.common.model.Progress;
import com.global.auth.kafka.consumer.UserUpdateEventDto;
import com.education.common.model.Rank;
import com.global.auth.model.UserEntity;
import com.global.auth.model.UserProvider;
import com.global.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;
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

    @Transactional
    public UserEntity createUser() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("john");
        userEntity.setPassword(new BCryptPasswordEncoder().encode("john"));
        userEntity.setRank(Rank.TRAINEE);
        userEntity.setEmail("john@email.com");
        userEntity.setRoles(getRoles());
        userRepository.save(userEntity);
        return userEntity;
    }

    private Collection<SimpleGrantedAuthority> getRoles() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }

}
