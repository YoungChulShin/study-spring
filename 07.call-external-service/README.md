# 설명
스프링에서 외부 서비스를 호출하기 위한 방법을 설명합니다. 

# 사용
## 등장 서비스 설명
Callee
- `SystemInfo` 라는 시스템 정보를 응답하는 서비스입니다.
- 의도적으로 에러를 만들기 위해서 5번째 호출마다 500 에러를 응답합니다. 
- `Caller`에 의해서 호출됩니다. 

Caller
- `사용자`에게 시스템 정보를 응답하는 요청을 받는 서비스입니다. 
- 하지만 `Caller`는 시스템 정보를 가지고 있지 않기 때문에, `Callee`를 호출해서 시스템 정보를 가져옵니다. 
- Caller는 3개의 방법으로 Callee를 호출합니다. 
   1. RestTemplate을 이용한 호출.
   2. WebClient를 이용한 호출.
   3. OpenFeign을 이용한 호출.

## 아키텍처
헥사고날 아키텍처를 적용했습니다. 구현체의 변경은 `SystemInfoPort`의 구현체에 따라서 달라집니다. 

구현체를 선택하는 방법은 `ConditionalOnProperty`를 이용해서 반영합니다. 
```yaml
system-info:
  external-call:
    type: resttemplate  # resttemplate, webcline, openfeign
```
```java
@Configuration
@ConditionalOnProperty(value = "system-info.external-call.type", havingValue = "resttemplate")
class RestTemplateConfiguration {
```

## RestTemplate
RestTemplate을 이용하면 동기적인 방식으로 외부 서비스를 호출할 수 있습니다.

`RestTemplateSystemInfo` 클래스에서 RestTemplate을 이용한 외부 서비스 호출 예시가 있습니다. 
```java
public SystemInfo getSystemInfo() {
    String url = "http://localhost:8080/api/v1/system";

    ResponseEntity<SystemInfo> response = restTemplate.exchange(
        url,
        HttpMethod.GET,
        null,
        SystemInfo.class);

    return response.getBody();
}
```

### ObjectMapper Converter
RestTemplate을 이용해서 데이터를 가져오고 응답 모델로 역직렬화 할 때, ObjectMapper의 설정이 영향을 줄 수 있습니다. 예를 들어서 요청모델에는 있는 필드가 응답모델에는 없다면 ObjectMapper의 기본 설정으로 인해서 에러가 발생합니다. 

이때는 ObjectMapper의 설정을 변경해줘야합니다. `RestTemplateConfiguration`에 관련 코드가 있습니다. 
```java
 ObjectMapper objectMapper = new ObjectMapper();
// 요청 데이터에는 있지만, 역질렬화 대상이 되는 클래스에는 없을 경우 에러 발생 여부 설정
objectMapper.disable(FAIL_ON_UNKNOWN_PROPERTIES);
objectMapper.disable(FAIL_ON_NULL_FOR_PRIMITIVES);
objectMapper.enable(READ_UNKNOWN_ENUM_VALUES_AS_NULL);

var converter = new MappingJackson2HttpMessageConverter();
converter.setObjectMapper(objectMapper);
```

### Exception Handler
RestTemplate에서 에러가 발생했을 때, 에러를 핸들링할 수 있습니다. 

`ResponseErrorHandler`의 구현체를 생성하면 됩니다. 인터페이스는 2개의 메서드를 가지고 있습니다. 
- hasError: 어떤 응답을 에러로 처리할지 결정합니다. 
- handleError: 에러가 발생했을 때 어떻게 처리할지 정의합니다. 

## WebClient

## OpenFeign
