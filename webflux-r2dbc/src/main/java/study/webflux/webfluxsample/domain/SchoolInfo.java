package study.webflux.webfluxsample.domain;

public record SchoolInfo(Long id, String name) {

    public static SchoolInfo from(School school) {
        return new SchoolInfo(school.getId(), school.getName());
    }
}
