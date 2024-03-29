package study.spring.security.jwt_security3.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
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
public class UserServiceImpl implements UserService, UserDetailsService {

  private final UserRepo userRepo;
  private final RoleRepo roleRepo;
  private final PasswordEncoder passwordEncoder;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepo.findByUsername(username);
    if (user == null) {
      log.error("User not found in the database");
      throw new UsernameNotFoundException("User not found in the database");
    } else {
      log.info("User found in the database: {}" , username);
    }

    Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
    user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));

    return new org.springframework.security.core.userdetails.User(
        user.getUsername(),
        user.getPassword(),
        authorities);
  }

  @Override
  public User saveUser(User user) {
    log.info("Saving new user {} to database", user.getName());
    User initUser = new User(
        user.getId(),
        user.getName(),
        user.getUsername(),
        passwordEncoder.encode(user.getPassword()),
        user.getRoles());
    return userRepo.save(initUser);
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
