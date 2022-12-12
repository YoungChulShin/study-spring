package study.webflux.webfluxtest4.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    private Long id;
    private String name;
    private String surname;
    private String username;
    private String email;
    private String password;

    public void update(User newUser) {
        if (newUser.getName() != null) {
            this.name = newUser.getName();
        }
        if (newUser.getSurname() != null) {
            this.surname = newUser.getSurname();
        }
        if (newUser.getUsername() != null) {
            this.username = newUser.getUsername();
        }
        if (newUser.getEmail() != null) {
            this.email = newUser.getEmail();
        }
    }
}
