package study.spring.osiv_lazylaoding.presentation.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import study.spring.osiv_lazylaoding.application.model.StudentInfo;

@Getter
@AllArgsConstructor
public class GetStudentResponse {

  private StudentInfo info;
}
