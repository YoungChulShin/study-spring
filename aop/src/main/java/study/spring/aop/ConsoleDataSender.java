package study.spring.aop;

import org.springframework.stereotype.Component;

@Component
public class ConsoleDataSender implements DataSender {

    @Override
    public void send(String data) {
        System.out.println("데이터 전송 : " + data);
    }
}
