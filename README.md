# 저장소 설명
스프링을 공부하면서 관련된 코드를 기록합니다. 

# 내용
|폴더|내용|링크|
|--|--|--|
|01.Request-Response|API를 개발할 때, 기본적인 요청/응답/로그 기능을 구현합니다.|[link](./01.request-response/)|
|02.Jpa-Basic|JPA 기본 기능을 구현합니다.<br>연관관계,querydsl 구현을 포함합니다.|[link](./02.jpa-basic/)|
|03.Redis-Cache|Redis를 이용해서 Global Cache를 구현합니다.|[link](./03.redis-cache/)|
|05.Configuration-Properties|application.yaml에 정의한 설정정보를 클래스로 매핑하는 방법을 알아봅니다.|[link](./05.configuration-properties/)|
|06.Bean-Conditional|Bean을 등록할 때, 조건에 따라서 등록 여부를 설정하는 방법을 알아봅니다.|[link](./06.bean-conditional/)|
|07.Call-External-Service|스프링에서 외부 API를 호출하는 방법을 알아봅니다.<br>도구: RestTemplate, HttpClient, OpenFeign|[link](/07.call-external-service/)|
|08.Fault-Tolerance|Fault-Tolerance 한 시스템을 구축하는 방법을 알아봅니다 .<br>예: Circuit-Breaker, Rate Limit, BulkHead|[link](/08.fault-tolerance/)|
|09.Transactional Outbox Pattern|Transactional Outbox Pattern과 Kafka를 이용해서<br>외부 시스템에 이벤트를 연동하는 방법을 알아봅니다.|[link](/09.data-transfer-distributed-system/data-transfer/)|
|10.Schedule-job|스프링에서 스케줄러를 사용하고, single/multi thread에서 차이를 알아봅니다. |[link](/10.schedule-job/)|

# 백로그
- 문서화
- tracing
- 메트릭 
- message를 이용한 연동