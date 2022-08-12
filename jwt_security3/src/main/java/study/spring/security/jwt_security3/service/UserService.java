package study.spring.security.jwt_security3.service;

import java.util.List;
import study.spring.security.jwt_security3.domain.Role;
import study.spring.security.jwt_security3.domain.User;

public interface UserService {

  User saveUser(User user);
  Role saveRole(Role role);
  void addRoleToUser(String username, String roleName);
  User getUser(String username);
  List<User> getUsers();
}
