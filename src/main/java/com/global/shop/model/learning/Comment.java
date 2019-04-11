package com.global.shop.model.learning;

import com.global.shop.model.CreatableEntity;
import com.global.shop.model.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author Aleksandr Sulyma
 * @version 1.0
 */
@Entity
@Table(name = "comment")
@NoArgsConstructor
@Getter
@Setter
public class Comment extends CreatableEntity {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id")
    private User author;

    @ManyToOne
    @JoinColumn(name = "lesson_id")
    private Lesson lesson;

    private String content;

}
