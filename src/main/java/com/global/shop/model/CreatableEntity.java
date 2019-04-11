package com.global.shop.model;

import com.global.shop.handler.CreatableEntityListener;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@ToString(callSuper = true)
@MappedSuperclass
@EntityListeners(value = CreatableEntityListener.class)
public class CreatableEntity extends BaseEntity {

    @Column(name = "created_date")
    private Long createdDate;
}
