package com.global.education.listener;

import com.global.education.model.CreatableEntity;

import java.util.Date;

import javax.persistence.PrePersist;

public class CreatableEntityListener {

    @PrePersist
    public void prePersist(CreatableEntity creatableEntity) {
        creatableEntity.setCreatedDate(new Date().getTime());
    }

}