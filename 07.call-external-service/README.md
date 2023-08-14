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
   1. v1: RestTemplate을 이용한 호출.
   2. v2: WebClient를 이용한 호출.
   3. v3: OpenFeign을 이용한 호출.

## RestTemplate
RestTemplate을 이용하면 동기적인 방식으로 외부 서비스를 호출할 수 있습니다.

`SystemInfoServiceV1` 클래스에서 RestTemplate을 이용한 외부 서비스 호출 예시가 있습니다. 
```java
public SystemInfo getSystemInfo() {
    String url = CalleeService.HOST + "/" + CalleeService.URL_SYSTEM_INFO;

    ResponseEntity<SystemInfo> response = restTemplate.exchange(
        url,
        HttpMethod.GET,
        null,
        SystemInfo.class);

    return response.getBody();
}
```

### ObjectMapper Converter

### Exception Handler

## WebClient

## OpenFeign
