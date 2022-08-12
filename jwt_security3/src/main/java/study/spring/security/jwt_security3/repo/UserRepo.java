package study.spring.security.jwt_security3.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import study.spring.security.jwt_security3.domain.User;

public interface UserRepo extends JpaRepository<User, Long> {

  User findByUsername(String username);
}
