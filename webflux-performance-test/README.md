# 배경
mvc, webflux 성능 비교 테스트

# 테스트 
mvc
```
❯ wrk -t20 -d30s -c200 http://localhost:8080/api/schools/2/students --latency
Running 30s test @ http://localhost:8080/api/schools/2/students
  20 threads and 200 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency     1.40s   302.21ms   2.00s    84.16%
    Req/Sec     8.77      6.17    50.00     77.20%
  Latency Distribution
     50%    1.40s
     75%    1.46s
     90%    1.74s
     99%    1.95s
  4009 requests in 30.06s, 28.68MB read
  Socket errors: connect 0, read 59, write 0, timeout 216
Requests/sec:    133.36
Transfer/sec:      0.95MB
```

