package study.spring.swagger.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.spring.swagger.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
