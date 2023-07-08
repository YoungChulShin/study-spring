package study.backend.java.database.adapter.out.persistence;

import java.util.List;
import study.backend.java.database.application.port.in.model.SchoolInfo;

public interface SchoolJpaRepositoryCustom {

  List<SchoolInfo> findAllSchoolInfos();

}
