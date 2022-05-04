### docker run -it --rm --name rabbit-demo -p 5672:5672 -p 15672:15672 rabbitmq:3-management
//-it i랑 t 옵션 합쳐서 쓴거
//-rm container가 멈추면 삭제
//-p {rabbitMQ 자체 포트} {rabbitMQ management 포트}

### java -jar -Dserver.port = {포트번호} build/libs/{스냅샷 이름}.jar

![image](https://user-images.githubusercontent.com/80913353/166224647-8e2fa077-5bd1-494b-adab-ceeff25a5e4f.png)

// producer to consumer

같은 Queue에 여러 consumer-> round robin 방식으로 결과를 나눠줌

![image](https://user-images.githubusercontent.com/80913353/166225556-f4706426-3dd7-4256-aec2-858638f6984e.png)
fanoutExchange -> 모든 큐에 broad casting

https://blog.dudaji.com/general/2020/05/25/rabbitmq.html : 엄청난 이해!..

## redis

redis image : reids:6-alpine
