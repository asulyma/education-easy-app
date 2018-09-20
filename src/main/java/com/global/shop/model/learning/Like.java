package com.global.shop.model.learning;

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
class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne()
    @JoinColumn(name = "lesson_id")
    private Lesson lesson;

    @ElementCollection
    @CollectionTable(name = "user_likes_mapping", joinColumns = @JoinColumn(name = "like_id"))
    @Column(name = "user_id")
    private List<Long> users;

    private Long valueLike;

    private Long valueDislike;
}
