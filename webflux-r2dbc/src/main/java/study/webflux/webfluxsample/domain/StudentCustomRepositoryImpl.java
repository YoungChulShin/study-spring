package study.webflux.webfluxsample.domain;

import org.jetbrains.annotations.NotNull;
import org.springframework.r2dbc.core.DatabaseClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.function.Function;

public class StudentCustomRepositoryImpl implements StudentCustomRepository {

    private final DatabaseClient databaseClient;

    public StudentCustomRepositoryImpl(DatabaseClient databaseClient) {
        this.databaseClient = databaseClient;
    }

    @Override
    public Mono<Student> findStudentById(Long id) {
        var sql = """
                SELECT s.id, s.name, s.age, s.gender, sc.id as schoolId, sc.name as schoolName
                FROM  students s
                INNER JOIN  schools sc
                        ON  s.school_id = sc.id
                WHERE s.id = :id 
                ORDER BY s.name
                """;

        return databaseClient.sql(sql)
                .bind("id", id)
                .fetch().one()
                .map(row -> {
                    var school = new School((Long) row.get("schoolId"), (String) row.get("schoolName"));
                    return new Student(
                            (Long) row.get("id"),
                            (String) row.get("name"),
                            (int) row.get("age"),
                            Gender.valueOf((String) row.get("gender")),
                            school.getId(),
                            school);
                });
    }

    @Override
    public Flux<StudentInfo> findStudentInfos() {
        var sql = """
                SELECT s.id, s.name, s.age, s.gender, sc.name as schoolName
                FROM  students s
                INNER JOIN  schools sc
                        ON  s.school_id = sc.id
                ORDER BY s.name
                """;
        return databaseClient.sql(sql)
                .fetch().all()
                .map(studentInfoMap());
    }

    @Override
    public Mono<StudentInfo> findStudentInfoById(Long id) {
        var sql = """
                SELECT s.id, s.name, s.age, s.gender, sc.name as schoolName
                FROM  students s
                INNER JOIN  schools sc
                        ON  s.school_id = sc.id
                WHERE s.id = :id
                """;

        return databaseClient.sql(sql)
                .bind("id", id)
                .fetch().one()
                .map(studentInfoMap());
    }

    @NotNull
    private Function<Map<String, Object>, StudentInfo> studentInfoMap() {
        return row -> new StudentInfo(
                (Long) row.get("id"),
                (String) row.get("name"),
                (int) row.get("age"),
                Gender.valueOf((String) row.get("gender")),
                (String) row.get("schoolName"));
    }
}
