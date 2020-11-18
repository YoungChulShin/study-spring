package study.spring.async_transactional.common;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;
import study.spring.async_transactional.team.Team;
import study.spring.async_transactional.team.TeamService;

@Component
@Slf4j
@RequiredArgsConstructor
public class MemberEventListener {

    private final TeamService teamService;

    @Async
    @EventListener
    public void handlerMemberCreatedEventNormal(MemberCreatedEvent memberCreatedEvent) {

        log.info("handlerMemberCreatedEventNormal - Start : " + Thread.currentThread().getId());

        Long savedTeam = teamService.saveOneSync(memberCreatedEvent.getTeamName());
        log.info("handlerMemberCreatedEventNormal - Team created");

        for (long i = 1; i <= 1000000000; i++) {

        }

        for (long i = 1; i <= 1000000000; i++) {

        }

        for (long i = 1; i <= 1000000000; i++) {

        }

        for (long i = 1; i <= 1000000000; i++) {

        }

        if (1==1) {
            throw new RuntimeException();
        }


    }
//
//    @Async
//    //@Transactional
//    @TransactionalEventListener
//    public void handlerMemberCreatedEventTransactional(MemberCreatedEvent memberCreatedEvent) {
//
//        log.info("handlerMemberCreatedEventTransactional - Start : " + Thread.currentThread().getId());
//
//        Long savedTeam = teamService.saveOneSync(memberCreatedEvent.getTeamName());
//        log.info("handlerMemberCreatedEventTransactional - Team created");
//
//        for (long i = 1; i <= 1000000000; i++) {
//
//        }
//
//        for (long i = 1; i <= 1000000000; i++) {
//
//        }
//
//        for (long i = 1; i <= 1000000000; i++) {
//
//        }
//
//        for (long i = 1; i <= 1000000000; i++) {
//
//        }
//
//        if (1 == 1) {
//            throw new RuntimeException(`"test");
//        }
//
//    }

//    @Async
//    @EventListener
//    public void handlerMemberCreatedEventNormal2(MemberCreatedEvent memberCreatedEvent) {
//        log.info("handlerMemberCreatedEventNormal2 - Start : " + Thread.currentThread().getId());
//
//        for (long i = 1; i <= 1000000000; i++) {
//
//        }
//
//        for (long i = 1; i <= 1000000000; i++) {
//
//        }
//
//        for (long i = 1; i <= 1000000000; i++) {
//
//        }
//        for (long i = 1; i <= 1000000000; i++) {
//
//        }
//    }
}
