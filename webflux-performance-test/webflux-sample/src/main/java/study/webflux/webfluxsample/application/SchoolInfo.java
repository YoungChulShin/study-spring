package study.webflux.webfluxsample.application;

import study.webflux.webfluxsample.domain.School;

public record SchoolInfo(Long id, String name) {

    public static SchoolInfo from(School school) {
        return new SchoolInfo(school.getId(), school.getName());
    }
}
