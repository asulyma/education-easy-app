package com.global.auth.model;

import com.education.common.model.Progress;
import com.education.common.model.Rank;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "user_table")
@Accessors(chain = true)
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
@NoArgsConstructor
public class UserEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "roles", joinColumns = @JoinColumn(name = "user_id"))
    private Collection<SimpleGrantedAuthority> roles = new HashSet<>();

    @Column(name = "email")
    private String email;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "rank")
    private Rank rank;

    // CourseId to Progress
    @Column(name = "progress", columnDefinition = "jsonb")
    @Type(type = "jsonb", parameters = {@Parameter(name = "classType", value = "java.util.HashMap")})
    private Map<Long, Progress> progressMap = new HashMap<>();
}
