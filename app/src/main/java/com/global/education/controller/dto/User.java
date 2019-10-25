package com.global.education.controller.dto;

import com.education.common.model.Progress;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Accessors(chain = true)
public class User {

    private UUID uuid;
    private String username;
    private String email;
    private Set<String> roles;
    private String rank;
    private Map<Long, Progress> progressMap = new HashMap<>();
}
