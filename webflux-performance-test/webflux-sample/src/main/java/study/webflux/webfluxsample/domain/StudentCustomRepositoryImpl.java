package study.webflux.webfluxsample.domain;

import org.springframework.r2dbc.core.DatabaseClient;
import reactor.core.publisher.Flux;

public class StudentCustomRepositoryImpl implements StudentCustomRepository {

    private final DatabaseClient databaseClient;

    public StudentCustomRepositoryImpl(DatabaseClient databaseClient) {
        this.databaseClient = databaseClient;
    }

    @Override
    public Flux<StudentInfo> findAllWithSchool() {
        var sql = """
                SELECT s.id, s.name, s.age, s.gender, sc.name as schoolName
                FROM  students s
                INNER JOIN  schools sc
                        ON  s.school_id = sc.id
                ORDER BY s.name
                """;

        return databaseClient.sql(sql)
                .fetch().all()
                .map(row -> new StudentInfo(
                        (Long) row.get("id"),
                        (String) row.get("name"),
                        (int) row.get("age"),
                        Gender.valueOf((String) row.get("gender")),
                        (String) row.get("schoolName")));
    }

    @Override
    public Flux<StudentInfo> findAllWithSchool(Long schoolId) {
        var sql = """
                SELECT s.id, s.name, s.age, s.gender, sc.name as schoolName
                FROM  students s
                INNER JOIN  schools sc
                        ON  s.school_id = sc.id
                WHERE s.school_id = :schoolId
                ORDER BY s.name
                """;

        return databaseClient.sql(sql)
                .bind("schoolId", schoolId)
                .fetch().all()
                .map(row -> new StudentInfo(
                        (Long) row.get("id"),
                        (String) row.get("name"),
                        (int) row.get("age"),
                        Gender.valueOf((String) row.get("gender")),
                        (String) row.get("schoolName")));
    }
}
