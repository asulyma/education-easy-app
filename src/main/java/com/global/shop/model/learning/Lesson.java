package com.global.shop.model.learning;

import com.global.shop.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author Aleksandr Sulyma
 * @version 1.0
 */
@Entity
@Table(name = "lesson")
@NoArgsConstructor
@Getter
@Setter
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(max = 256)
    private String title;

    @Size(max = 2048)
    private String description;

    private Long authorId;

    @Version
    @Column(name = "update_date")
    private Timestamp updateDate;

    @ManyToOne()
    @JoinColumn(name = "section_id")
    private Section section;

    @ManyToMany(mappedBy = "allowedLessons")
    @Column(name = "allowed_users")
    private List<User> allowedUsers;

    @ManyToMany(mappedBy = "alreadyDoneLesson")
    @Column(name = "already_done")
    private List<User> alreadyDone;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "like_id")
    private Like lessonLike;

    @OneToMany(mappedBy = "lesson", cascade = CascadeType.ALL)
    private List<Comment> comment;
}
