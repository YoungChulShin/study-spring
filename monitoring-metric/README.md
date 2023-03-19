# 저장소 설명
'Actuator'와 'Micrometer'를 이용해서 프로그램의 메트릭을 어떻게 추출하는지 설명합니다.

추출되는 메트릭은 '프로메테우스'에서 읽어가는 것을 전제합니다.

# 링크
Actuator, 프로메테우스, 그라파나 설정에 대한 추가 설명: [link](https://github.com/YoungChulShin/inflearn-springboot-core-and-use/tree/master/actuator)

# 데이터 추출 방법
## 1. 'spring-boot-starter-actuator' 의존성 추가
```kts
implementation("org.springframework.boot:spring-boot-starter-actuator")
```
의존성이 추가되면 `'/actuator'` 경로에서 대상 항목을 조회할 수 있다

## 2. metric 설정 수정
```yml
management:
  endpoints:
    web:
      exposure:
        # 어떤 항목을 노출할 것인지를 적어준다
        # 모든 항목을 다 보려면 '*'를 입력한다
        include: "health, metrics, prometheus"
  server:
    # actuator 포트를 서버 포트와 분리해서 보안 정책을 달리 가져간다
    port: 8090
```
실제로 우리가 확인할 지표는 'metrics' 지표이다. 설정 변경 이후에 서버를 실행하면 '/actuator/metrics' 경로에서 지표 리스트를 확인할 수 있다.

## 3. JPA 의존성 추가 -> ConnectionPool 지표 확인
```kts
implementation("org.springframework.boot:spring-boot-starter-data-jpa")
runtimeOnly("com.h2database:h2")
```
JPA 의존성을 추가하면 HikariCP의 메트릭을 추가로 수집할 수 있다

## 4. tomcat 설정 변경을 통해서 추가적인 지표 출력
```yml
server:
  tomcat:
    mbeanregistry:
      enabled: true
```
위 설정을 활성화하면 기본으로 보이는 지표 외에 추가적인 톰캣 지표를 수집할 수 있다
```
// 기존에는 session에 대한 지표만 표시되었고, 설정 변경 이후에는 추가적인 정보를 확인할 수 있다
"tomcat.cache.access",
"tomcat.cache.hit",
"tomcat.connections.config.max",
"tomcat.connections.current",
"tomcat.connections.keepalive.current",
"tomcat.global.error",
"tomcat.global.received",
"tomcat.global.request",
"tomcat.global.request.max",
"tomcat.global.sent",
"tomcat.servlet.error",
"tomcat.servlet.request",
"tomcat.servlet.request.max",
"tomcat.sessions.active.current",
"tomcat.sessions.active.max",
"tomcat.sessions.alive.max",
"tomcat.sessions.created",
"tomcat.sessions.expired",
"tomcat.sessions.rejected",
"tomcat.threads.busy",
"tomcat.threads.config.max",
"tomcat.threads.current"
```

## 5. 마이크로미터-프로메테우스 의존성 추가
```kts
implementation("io.micrometer:micrometer-registry-prometheus")
```
micrometer는 공통된 타입으로 데이터를 수집해서, 특정 구현체에 맞는 메트릭을 생성해줄 수 있다. 여기서는 프로메테우스를 위한 지표를 추가한다. 

위 의존성을 추가하면 `'actuator/prometheus`' 경로로 프로메테우스 지표를 확인할 수 있다. 기존에 '/actuator/metrics'의 지표를 프로메테우스의 양식에 맞게 변경된 지표를 볼 수 있다. 

