package com.global.auth.service;

import static org.springframework.security.core.authority.AuthorityUtils.createAuthorityList;

import java.util.Collections;
import java.util.UUID;

import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.education.common.dto.event.UserCreationEvent;
import com.global.auth.model.UserEntity;
import com.global.auth.repository.UserRepository;
import com.global.auth.repository.projection.UserUuid;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}

		String[] roles = new String[user.getRoles().size()];
		return new User(user.getUsername(), user.getPassword(), createAuthorityList(user.getRoles().toArray(roles)));
	}

	public UUID loadUserUuid(String username) {
		UserUuid uuid = userRepository.findUuidByUsername(username);
		if (uuid == null) {
			throw new UsernameNotFoundException(username);
		}
		return uuid.getUuid();
	}

	@Transactional
	public void createUser(UserCreationEvent userDto) {
		UserEntity userEntity = new UserEntity();
		userEntity.setUsername(userDto.getUsername());
		userEntity.setPassword("{bcrypt}" + new BCryptPasswordEncoder().encode(userDto.getPassword()));
		userEntity.setRoles(Collections.singleton(userDto.getRole()));
		userRepository.save(userEntity);

		log.info("User has been created! Name: {}, UUID: {}", userEntity.getUsername(), userEntity.getUuid());
	}

}
