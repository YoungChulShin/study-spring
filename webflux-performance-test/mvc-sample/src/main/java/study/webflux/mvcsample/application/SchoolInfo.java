package study.webflux.mvcsample.application;

import study.webflux.mvcsample.domain.School;

public record SchoolInfo(Long id, String name) {

    public static SchoolInfo from(School school) {
        return new SchoolInfo(school.getId(), school.getName());
    }
}
