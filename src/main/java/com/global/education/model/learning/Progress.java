package com.global.education.model.learning;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@AllArgsConstructor
@Accessors(chain = true)
@NoArgsConstructor
public class Progress {

    private String courseName;
    private Long progress;

}
