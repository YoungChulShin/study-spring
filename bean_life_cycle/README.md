## 저장소 설명
`초보 웹 개발자를 위한 스프링5 프로그래밍 입문` 책에 있는 빈 라이프사이클 테스트

## 리이프사이클 설명
스프링컨테이너는 빈 객체의 라이프사이클을 관리한다. 

라이프사이클
- 객체 생성
- 의존 설정
- 초기화
   - InitializeBean interface
- 소멸
   - DispoableBean interface

직접 생성한 클래스는 이렇게 초기화/소멸 코드를 정의할 수 있지만 외부에서 제공받는 클래스는 제한이 있는데, 빈을 선언할 때 초기화/소멸 메서드를 지정해주면 된다. 
- 샘플 코드
    ~~~java
    @Bean(initMethod = "connect", destroyMethod = "close")
    public Client2 client2() {
        Client2 client = new Client2();
        client.setHost("host");
        return client;
    }
    ~~~

스프링컨테이너는 기본적으로 빈 객체를 한개만 생성하기 때문에 싱글톤의 범위를 갖는다. 매번 새로운 객체를 생성하고 싶다면 빈의 범위를 `prototype`으로 설정해준다