package com.global.education.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "course")
public class TranslationHolder {

    private String infoToUser;
    private String permissionToAdmin;
    private String approvePermissionToUser;
    private String approvePermissionToAdmin;
    private String declinePermissionToUser;
    private String declinePermissionToAdmin;


}
