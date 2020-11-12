package study.spring.async_transactional.team;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TeamService {

    private final TeamRepository teamRepository;

    @Async
    public Long saveOne(String name) {
        log.info("TeamService - start save");
        Team team = Team.create(name);
        Team savedTeam = teamRepository.save(team);
        log.info("TeamService - end save");

        return savedTeam.getId();
    }

    @Transactional
    public Long saveOneSync(String name) {
        log.info("TeamService - start save");
        Team team = Team.create(name);
        Team savedTeam = teamRepository.save(team);
        log.info("TeamService - end save");

        return savedTeam.getId();
    }
}
