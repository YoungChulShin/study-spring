package study.spring.webclienttest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {

    private String name;
    private String surname;
    private String username;
    private String email;
    private String password;
}
