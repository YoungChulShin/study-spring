package study.spring.security.jwt_security3.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.spring.security.jwt_security3.domain.Role;
import study.spring.security.jwt_security3.domain.User;
import study.spring.security.jwt_security3.repo.RoleRepo;
import study.spring.security.jwt_security3.repo.UserRepo;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {

  private final UserRepo userRepo;
  private final RoleRepo roleRepo;

  @Override
  public User saveUser(User user) {
    log.info("Saving new user {} to database", user.getName());
    return userRepo.save(user);
  }

  @Override
  public Role saveRole(Role role) {
    log.info("Saving new role {} to database", role.getName());
    return roleRepo.save(role);
  }

  @Override
  public void addRoleToUser(String username, String roleName) {
    log.info("Adding role {} to user {}", roleName, username);
    User user = userRepo.findByUsername(username);
    Role role = roleRepo.findByName(roleName);

    user.getRoles().add(role);
  }

  @Override
  public User getUser(String username) {
    log.info("Fetching user {}", username);
    return userRepo.findByUsername(username);
  }

  @Override
  public List<User> getUsers() {
    log.info("Fetching all users");
    return userRepo.findAll();
  }
}
