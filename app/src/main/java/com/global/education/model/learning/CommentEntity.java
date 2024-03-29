package com.global.education.model.learning;

import com.global.education.model.CreatableEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "comment")
@Accessors(chain = true)
@NoArgsConstructor
public class CommentEntity extends CreatableEntity {

    @Column(name = "author_uuid")
    private UUID authorUuid;

    @Column(name = "author_name")
    private String authorName;

    @ManyToOne
    @JoinColumn(name = "lesson_id")
    private LessonEntity lesson;

    @Column(name = "content")
    private String content;

}
