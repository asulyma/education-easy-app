package com.global.shop.handler;

import com.global.shop.model.CreatableEntity;

import java.util.Date;

import javax.persistence.PrePersist;

public class CreatableEntityListener {

    @PrePersist
    public void prePersist(CreatableEntity creatableEntity) {
        creatableEntity.setCreatedDate(new Date().getTime());
    }

}