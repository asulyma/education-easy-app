package com.global.education.model.listener;

import com.global.education.model.CreatableEntity;

import java.util.Date;

import javax.persistence.PrePersist;

public class CreatableEntityListener {

    @PrePersist
    public void prePersist(CreatableEntity creatableEntity) {
        creatableEntity.setCreatedDate(new Date().getTime());
    }

}