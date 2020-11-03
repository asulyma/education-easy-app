package com.global.auth.model;

import java.util.*;

import javax.persistence.*;

import lombok.*;


@Getter
@Setter
@Entity
@Table(name = "user_table")
@NoArgsConstructor
public class UserEntity {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "uuid")
	private UUID uuid;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
	private Collection<String> roles = new HashSet<>();

	@PrePersist
	public void prePersist() {
		if (uuid == null) {
			uuid = UUID.randomUUID();
		}
	}
}
