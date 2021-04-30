package io.github.leitess.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum PhoneType {

    HOME("Home"),
    MOBILE("Mobile"),
    COMMERCIAL("Commercial");

    private final String description;

    public static PhoneType of(String description) {

        return Stream.of(PhoneType.values())
                .filter(it -> it.getDescription().equals(description))
                .findFirst()
                .orElseThrow();
    }
}
