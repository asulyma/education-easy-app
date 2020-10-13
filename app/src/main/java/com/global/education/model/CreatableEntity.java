package com.global.education.model;

import com.global.education.model.listener.CreatableEntityListener;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(value = CreatableEntityListener.class)
public class CreatableEntity extends BaseEntity {

    @Column(name = "created_date")
    private Long createdDate;
}
