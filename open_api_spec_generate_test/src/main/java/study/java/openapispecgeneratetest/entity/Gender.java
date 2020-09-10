package study.java.openapispecgeneratetest.entity;

import lombok.Getter;

@Getter
public enum Gender {
    Male("male"),
    Female("female");

    private final String valueString;

    Gender(final String valueString) {
        this.valueString = valueString;
    }

    public static Gender fromString(String text) {
        for (Gender gender : Gender.values()) {
            if (gender.getValueString().equalsIgnoreCase(text)) {
                return gender;
            }
        }

        throw new IllegalArgumentException();
    }
}
