package com.global.shop.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Pojo class.
 *
 * @author Aleksandr Sulyma
 * @version 1.0
 */
@Entity
@Table(name = "user")
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private long id;

    @Size(min = 2, max = 32)
    @NotNull(message = "Enter your name!")
    @Getter
    @Setter
    private String name;

    @Size(min = 2, max = 32)
    @NotNull(message = "Enter your surname!")
    @Getter
    @Setter
    private String surname;

    @Email
    @Column(unique = true)
    @Getter
    @Setter
    private String email;
}
