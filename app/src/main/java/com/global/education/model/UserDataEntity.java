package com.global.education.model;

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

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "user_data")
@Accessors(chain = true)
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
@NoArgsConstructor
public class UserDataEntity extends BaseEntity {

    @Column(name = "uuid")
    private UUID uuid;

    @Column(name = "username")
    private String username;

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
