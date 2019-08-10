package com.global.education.config;

import com.global.education.model.notification.NotificationType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

import static com.global.education.model.notification.NotificationType.APPROVE_PERMISSION_INFO_TO_ADMIN;
import static com.global.education.model.notification.NotificationType.APPROVE_PERMISSION_INFO_TO_USER;
import static com.global.education.model.notification.NotificationType.DECLINE_PERMISSION_INFO_TO_ADMIN;
import static com.global.education.model.notification.NotificationType.DECLINE_PERMISSION_INFO_TO_USER;
import static com.global.education.model.notification.NotificationType.INFO_TO_USER;
import static com.global.education.model.notification.NotificationType.PERMISSION_TO_ADMIN;

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

    public Map<NotificationType, String> getPair() {
        Map<NotificationType, String> pair = new HashMap<>();
        pair.put(INFO_TO_USER, infoToUser);
        pair.put(PERMISSION_TO_ADMIN, permissionToAdmin);
        pair.put(APPROVE_PERMISSION_INFO_TO_USER, approvePermissionToUser);
        pair.put(APPROVE_PERMISSION_INFO_TO_ADMIN, approvePermissionToAdmin);
        pair.put(DECLINE_PERMISSION_INFO_TO_USER, declinePermissionToUser);
        pair.put(DECLINE_PERMISSION_INFO_TO_ADMIN, declinePermissionToAdmin);
        return pair;
    }

}
