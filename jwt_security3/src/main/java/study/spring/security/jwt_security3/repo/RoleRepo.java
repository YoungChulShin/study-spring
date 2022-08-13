package study.spring.security.jwt_security3.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import study.spring.security.jwt_security3.domain.Role;

public interface RoleRepo extends JpaRepository<Role, Long> {

  Role findByName(String name);
}
