package org.haram.rothem.data.type;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

@Getter
public enum NoticeType {
    HOME("ROLE_GUEST"),
    ROTHEM("ROLE_CTL"),
    STUDENT("ROLE_STUDENT_BODY"),
    BIBLE("ROLE_ADMIN"),
    SPACE("ROLE_SPACE"),
    ;

    NoticeType(String key) {
        this.key = key;
    }

    private final String key;

    public String enumToString() {
        return this.name();
    }

    public static String enumToString(NoticeType role) {
        return role.name();
    }

    public static NoticeType toEnum(@NotNull String string) {
        return Arrays.stream(values())
                .filter(value -> value.getKey().equals(string))
                .findFirst()
                .orElse(null);
    }
}
