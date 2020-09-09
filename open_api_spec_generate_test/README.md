### 목적
Open Api Spec. 을 이용해서 코드 생성 및 활용을 테스트 한다

### Open Api Spec. 
- Github: [https://github.com/OAI/OpenAPI-Specification](https://github.com/OAI/OpenAPI-Specification)
- 3.0.3 Spec: [Link](https://github.com/OAI/OpenAPI-Specification/blob/master/versions/3.0.3.md)

### API Spec. 
간단한 Member 을 생성하고, 수ㄴㅁ정하고, 조회하고, 삭제하는 Api를 만들어본다

Member 생성
- POST
- /api/members
- name, age, gender

Member 수정
- Put
- /api/members/{member id}
- name, age, gender

Member 삭제
- Delete
- /api/members/{member id}

Member 조회
- Get
- /api/members, /api/members/{member id}
