package com.global.auth.model;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "user_table")
@Accessors(chain = true)
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class UserEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "active")
    private boolean active;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "rank")
    private Rank rank;

    @Column(name = "user_data", columnDefinition = "jsonb")
    @Type(type = "jsonb", parameters = {@Parameter(name = "classType", value = "java.util.HashMap")})
    private Map<DataType, List<Long>> userData = new HashMap<>();

    @Column(name = "progress", columnDefinition = "jsonb")
    @Type(type = "jsonb", parameters = {@Parameter(name = "classType", value = "com.global.auth.model.Progress")})
    private Progress progress = new Progress();
}
