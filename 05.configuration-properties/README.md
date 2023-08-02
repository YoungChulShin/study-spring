# 설명
`application.yaml`에 있는 설정을 `@Value`를 이용해서 직접 사용하는게 아니라, 별도의 클래스로 매핑해서 사용하는 방법을 알아봅니다.

# 사용
## 샘플 코드 정보
`applucation.yaml`에 저장된 `my-application` 이라는 임의의 애플리케이션 정보를 API 응답으로 전달하는 프로그램을 구현합니다. 

## 매핑 설정 방법
### 공통 설정
1. `@ConfigurationPropertiesScan` 애노테이션 추가
   ```java
   @ConfigurationPropertiesScan(basePackages = "study.spring.configurationproperties.config.model")
   ```
      
2. 설정 정보를 저장할 클래스를 생성한다. 
   - 예제에서는 MyApplicationInfo, MyApplicationInfoV2, MyApplicationInfoV3가 된다.
   - 클래스는 설정 정보와 매핑되는 필드를 가지고 있어야한다. 
   - __각 필드는 setter 또는 생성자를 통해서 값이 할당된다. 따라서 생성자를 통한 생성이 아니라면 setter를 정의해줘야한다.__
3. 설정 클래스에 `@ConfigurationProperties` 애노테이션을 추가한다. 
   ```java
   @ConfigurationProperties(prefix = "my-application")
   ```
   - `prefix` 옵션에는 `application.yaml`에서 선언한 설정 값의 prefix를 적어준다. 
4. `build.gradle`에 dependency를 추가한다. 
   ```groovy
   annotationProcessor "org.springframework.boot:spring-boot-configuration-processor"
   ```
   - 이를 통해서 application.yaml에 정의한 설정 값과 이 정보를 담을 클래스가 연결된다. yaml의 속성 정보를 보면 코드를 찾아갈 수 있고, 코드에서 정의한 정보를 읽을 수 있다. 
      

### 설정 정보를 저장하는 클래스 구현방법
예제에서는 3개의 방법으로 구현했다. 

방법 1: setter를 이용한 값 설정
- 필드를 선언하고, 각 필드에 setter를 선언해준다. 
- MyApplicationInfo 클래스를 참고하면 된다. 

방법 2: 생성자 이용
- 생성자를 이용해서 바로 값을 설정해줄 수 있다. 
- MyApplicationInfoV2 클래스를 참고하면 된다. 
- setter를 선언하지 않아도 되기 때문에, 필드를 final로 선언해줄 수 있는 장점이 있다. 

방법 3: Record 이용
- 생성자를 이용하는 방법과 같은 개념인데, 자바에서 제공하는 `Record`를 이용하면 코드를 더 간략하게 작성할 수 있는 장점이 있다. 
- MyApplicationInfoV3 클래스를 참고하면 된다. 