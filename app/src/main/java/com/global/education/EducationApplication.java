package com.global.education;

import com.global.education.config.TranslationHolder;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.TimeZone;

import static org.springframework.boot.context.config.ConfigFileApplicationListener.CONFIG_LOCATION_PROPERTY;

@EnableConfigurationProperties({TranslationHolder.class})
@SpringBootApplication
@EnableAspectJAutoProxy
public class EducationApplication {

    static {
        //required for correct work of JDBC driver
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    private static final String APP_PROPERTIES = "classpath:application.yml, classpath:translation.yml";

    public static void main(String[] args) {
        new SpringApplicationBuilder(EducationApplication.class)
                .properties(String.format("%s=%s", CONFIG_LOCATION_PROPERTY, APP_PROPERTIES))
                .run(args);
    }
}
