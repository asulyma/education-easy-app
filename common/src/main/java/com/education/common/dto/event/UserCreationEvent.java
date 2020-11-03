package com.education.common.dto.event;

import javax.validation.constraints.NotNull;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
public class UserCreationEvent {

	@NotNull
	private String username;
	@NotNull
	private String password;
	@NotNull
	private String role;

}
