package com.global.education.controller.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Accessors(chain = true)
public class User {

    private Long id;
    private String username;
    private String email;
    private Set<String> roles;
    private String rank;
    private Map progressMap = new HashMap();
}
