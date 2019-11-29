package com.global.education.controller.dto;

import com.education.common.model.Progress;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Getter
@Setter
@Accessors(chain = true)
public class User {

    private UUID uuid;

    @NotNull
    private String username;
    @NotNull
    private String email;
    @NotNull
    private String rank;
    private Map<Long, Progress> progressMap = new HashMap<>();
}
