## Spring Gateway
![image](https://user-images.githubusercontent.com/80913353/167356360-92846360-4223-439c-9f4b-e8f34d5d18b4.png)

profile 별로 실행 설정

## Config Server
![image](https://user-images.githubusercontent.com/80913353/167393051-a2513c13-1fc2-4297-9bda-55cf1654a0b6.png)

현재 상태 확인

## file system config
![image](https://user-images.githubusercontent.com/80913353/167546549-c65c5757-cf9c-4549-88b2-e0bf29c4abd6.png)
![image](https://user-images.githubusercontent.com/80913353/167546755-0fb3c486-9cfe-405a-8dfa-a830a42cee70.png)

actuator 설정 후

{PROTOCOL}://{HOST}/actuator/refresh/로 

POST(정말 열받는다 ^^) 요청을 보내야한다

db 설정과 actutor 설정을 common에 몰아 넣고 모든 config에 뿌려줬는데

actuator관련 설정은 서버를 재시작해야 적용된다.(Config Server가 아닌 Service Server)

refresh가 actuator 서버에서 관할 하는거라 health를 추가한다거나 이런건 적용이 안된다.

당연한 말이지만 포트번호를 변경해도 refresh가 되진 않는다. 요것도 재시작해야함
