# 저장소 설명
HTTP Request와 Response 로그를 출력해보자. 

### Interceptor를 이용한 방식
`WebMvcConfigurer`에 interceptor를 추가해서 구현하는 방식. 

HttpServletRequest와 HttpServletResponse의 데이터를 BufferedRequestWrapper, BufferedResponseWrapper로 감싼다.

### Filter를 이용한 방식