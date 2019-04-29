package com.global.shop.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "course")
@Getter
public class TranslationConfig {

    private String infoToUser;
    private String permissionToAdmin;
    private String approvePermission;
    private String declinePermission;

}
