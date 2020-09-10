### 목적
Open Api Spec. 을 이용해서 코드 생성 및 활용을 테스트 한다

### Open Api Spec. 
- Github: [https://github.com/OAI/OpenAPI-Specification](https://github.com/OAI/OpenAPI-Specification)
- 3.0.3 Spec: [Link](https://github.com/OAI/OpenAPI-Specification/blob/master/versions/3.0.3.md)
- OpenApi Generate tool: [Link](https://github.com/OpenAPITools/openapi-generator/tree/master/modules/openapi-generator-gradle-plugin)

### API Spec. 
간단한 Member 을 생성하고, 수정정하고, 조회하고, 삭제하는 API를 만들어본다

Member 정보
- name: 이름
- age: 나이
- gender: 성별
- phone: 연락처

Member 생성
- POST
- /api/members
- name, age, gender, phone

Member 수정
- Put
- /api/members/{member id}
- name, age, gender, phone

Member 삭제
- Delete
- /api/members/{member id}

Member 조회
- Get
- /api/members, /api/members/{member id}

### 솔루션 빌드
실행
1. MySQL 실행 
   ~~~
   docker-compose -f scripts/mysql.yml up -d
   ~~~
2. 프로그램 실행
   ~~~
   ./gradlew clean bootRun
   ~~~

종료
1. MySQL 종료
   ~~~
   docker-compose -f scripts/mysql.yml down
   ~~~
2. 프로그램 종료

API 주소
- http://localhost:8080/