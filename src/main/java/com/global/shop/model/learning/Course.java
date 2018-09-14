package com.global.shop.model.learning;

import com.global.shop.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * @author Aleksandr Sulyma
 * @version 1.0
 */
@Entity
@Table(name = "course")
@NoArgsConstructor
@Getter
@Setter
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(max = 256)
    private String title;

    @Size(max = 1024)
    private String description;

    private LocalDate beginDate;

    private LocalDate endDate;

    @Min(value = 0)
    private BigDecimal cost;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Section> sections;

    @ManyToMany(mappedBy = "allowedCourses")
    @Column(name = "allowed_users")
    private List<User> allowedUsers;

    @Min(value = 0)
    @Max(value = 1000)
    private Long progress;
}
