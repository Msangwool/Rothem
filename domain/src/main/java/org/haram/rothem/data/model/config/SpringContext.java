package org.haram.rothem.data.model.config;

import org.jetbrains.annotations.NotNull;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class SpringContext implements ApplicationContextAware {

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(@NotNull ApplicationContext applicationContext) {
        context = applicationContext;
    }

    public static String getActiveProfile() {
        Environment environment = context.getEnvironment();
        String[] activeProfiles = environment.getActiveProfiles();
        return (activeProfiles.length > 0) ? activeProfiles[0] : "default";
    }

    public static String setDescriptionStatus(String message) {
        if (getActiveProfile().equals("prod")) {
            return "Please check document";
        }
        return message;
    }
}
