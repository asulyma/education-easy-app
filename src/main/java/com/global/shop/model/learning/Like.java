package com.global.shop.model.learning;

import com.global.shop.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * @author Aleksandr Sulyma
 * @version 1.0
 */
@Entity
@Table(name = "lesson_like")
@NoArgsConstructor
@Getter
@Setter
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(mappedBy = "lessonLike")
    private Lesson lesson;

    @ManyToMany(mappedBy = "likes")
    private List<User> users;

    private Long valueLike;

    private Long dislike;
}
