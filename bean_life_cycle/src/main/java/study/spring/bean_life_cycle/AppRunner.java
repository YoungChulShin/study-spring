package study.spring.bean_life_cycle;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 등록된 빈들이 한번에 초기화
        AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class);

        Client client = ctx.getBean(Client.class);
        client.send();

        Client2 client2 = ctx.getBean(Client2.class);
        client2.send();

        // 등록된 빈들이 한번에 소멸
        ctx.close();
    }
}
