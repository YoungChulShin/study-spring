# 배경
mvc, webflux 성능 비교 테스트

# 테스트 -1
### 테스트 케이스
테스트 API
- 학생 정보를 모두 호출하는 API 호출
- `http://localhost:8080/api/students`

트래픽
- 4개의 스레드에서 30초만 200개의 연결을 만들어서 호출
   ```
    wrk -t4 -d30s -c200 http://localhost:8080/api/students --latency
   ```

### mvc reesult
1차 
```
Running 30s test @ http://localhost:8080/api/students
  4 threads and 200 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency   458.02ms  562.11ms   1.99s    78.98%
    Req/Sec    19.08     10.93    60.00     85.06%
  Latency Distribution
     50%  142.28ms
     75%  471.65ms
     90%    1.53s
     99%    1.91s
  2108 requests in 30.03s, 154.59MB read
  Socket errors: connect 0, read 110, write 0, timeout 1794
Requests/sec:     70.21
Transfer/sec:      5.15MB
```

2차
```
Running 30s test @ http://localhost:8080/api/students
  4 threads and 200 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency   421.68ms  529.22ms   1.98s    82.49%
    Req/Sec    19.48     11.38    69.00     82.74%
  Latency Distribution
     50%  140.70ms
     75%  444.89ms
     90%    1.44s
     99%    1.94s
  2137 requests in 30.02s, 156.71MB read
  Socket errors: connect 0, read 158, write 0, timeout 1760
Requests/sec:     71.19
Transfer/sec:      5.22MB
```

### webflux result
1차
```
Running 30s test @ http://localhost:8080/api/students
  4 threads and 200 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency     1.72s   245.89ms   2.00s    90.00%
    Req/Sec    22.28     13.90    60.00     65.09%
  Latency Distribution
     50%    1.76s
     75%    1.83s
     90%    1.88s
     99%    1.99s
  2027 requests in 30.03s, 148.41MB read
  Socket errors: connect 0, read 130, write 0, timeout 427
Requests/sec:     67.49
Transfer/sec:      4.94MB
```

2차
```
Running 30s test @ http://localhost:8080/api/students
  4 threads and 200 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency     1.17s   482.09ms   1.99s    58.54%
    Req/Sec     7.39      5.26    30.00     78.93%
  Latency Distribution
     50%    1.24s
     75%    1.58s
     90%    1.88s
     99%    1.99s
  690 requests in 30.05s, 50.52MB read
  Socket errors: connect 0, read 137, write 0, timeout 649
Requests/sec:     22.97
Transfer/sec:      1.68MB
```

### 결과
MVC가 더 성능이 잘 나온다. 왜 그럴까..?