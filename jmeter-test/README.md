# 저장소 설명
jmeter를 이용해서 부하테스트를 해 본다

# 참고 자료
jmeter 설치
- homebrew 이용
   ```
   brew install jmeter
   ```

jmeter 공식 사이트의 `Get Started` 문서
- https://jmeter.apache.org/usermanual/get-started.html

# 테스트 메모
## 요구 사항
자바 버전: 8 이상

운영체제: 자바를 구동할 수 있는 환경
- JMeter는 100% 자바로 만들어진 프로그램이다
- https://cwiki.apache.org/confluence/display/JMETER/JMeterAndOperatingSystemsTested

## 설치
최신 Release 를 이용한 설치: http://jmeter.apache.org/download_jmeter.cgi

homebrew를 이용한 설치: `brew install jmeter`

## 프로그램 실행
프로그램 실행
- 다운로드 한 bin 폴더 내에 있는 `jmeter`파일을 이용해서 실행한다
- homebrew를 이용했으면, `jmeter` 명령어로 바로 실행 가능하다
   - `brew info jmeter`를 이용해서 설치 경로를 확인할 수 있다
   - 경로 내에서 `libexec/bin` 경로에서 파일을 찾을 수 있다

GUI Mode와 CLI Mode
- GUI Mode: test script를 생성할 때 사용
- CLI Mode: load test를 할 때 사용
   - `-n` 옵션 사용

옵션 리스트 확인
- 커맨드
   ```
   jmeter -\?
   ```
- 실행 시 추가적인 옵션은 아래 링크에서 확인할 수 있다
   - https://jmeter.apache.org/usermanual/get-started.html

## 테스트플랜 생성

## 테스트 실행
생성된 JMX 파일을 이용해서 Console에서 테스트 실행

옵션 정보
```
-n: This specifies JMeter is to run in cli mode
-t: [name of JMX file that contains the Test Plan].
-l: [name of JTL file to log sample results to].
-j: [name of JMeter run log file].
-r: Run the test in the servers specified by the JMeter property "remote_hosts"
-R: [list of remote servers] Run the test in the specified remote servers
-g: [path to CSV file] generate report dashboard only
-e: generate report dashboard after load test
-o :output folder where to generate the report dashboard after load test. Folder must not exist or be empty. The script also lets you specify the optional firewall/proxy server information:
-H: [proxy server hostname or ip address]
-P: [proxy server port]
```

테스트 결과 확인
- [jemter.log](/report-sample/jmeter.log): jemter 테스트 로그
- [test.log](/report-sample/test.log): JTL 로그
- [statistics.json](/report-sample/statistics.json): 테스트 통계 정보