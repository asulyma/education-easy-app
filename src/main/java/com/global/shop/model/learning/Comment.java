package com.global.shop.model.learning;

import com.global.shop.model.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * @version 1.0
 *
 * @author Aleksandr Sulyma
 */
@Entity
@Table(name = "comment")
@NoArgsConstructor
@Getter
@Setter
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id")
    private User author;

    @ManyToOne
    @JoinColumn(name = "lesson_id")
    private Lesson lesson;

    private String content;

    @Column(name = "update_date")
    private LocalDate updateDate;

}
