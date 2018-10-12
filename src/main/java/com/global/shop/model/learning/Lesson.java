package com.global.shop.model.learning;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.global.shop.model.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;
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

    @Column(name = "update_date")
    private LocalDate updateDate;

    @ManyToOne
    @JoinColumn(name = "section_id")
    @JsonIgnore
    private Section section;

    @ManyToMany(mappedBy = "allowedLessons")
    @Column(name = "allowed_users")
    private List<User> allowedUsers;

    @ManyToMany(mappedBy = "alreadyDoneLesson")
    @Column(name = "already_done")
    private List<User> alreadyDone;

    @OneToOne(mappedBy = "lesson", cascade = CascadeType.ALL)
    private Like lessonLike;

    @OneToMany(mappedBy = "lesson", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "lesson", allowSetters = true)
    private List<Comment> comment;
}
