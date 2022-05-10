# Mission 목표
* 사용자의 위치 정보를 받아서, 사용자의 현재 소재지를 파악하는 기능을 구현
* HTML Geolocation API를 사용
* AreaEntity 의 경우, 더미 데이터를 우선 활용
## 미션 스크린샷
![image](https://user-images.githubusercontent.com/80913353/167592952-10ec5352-df65-4b85-827e-c7a84b6f90af.png)


https://www.roseindia.net/jpa/eclipsejpaexamples/abs.shtml jpa reference
https://developer.mozilla.org/ko/docs/Web/API/Fetch_API/Using_Fetch : fetch

분명 jpql도 abs로 sorting 할 수 있다는거 같은데 실행이 안된다.

일단 native query로 해결하긴 했지만 아직 더 공부해봐야겠다.

생각해보니까 db에서 nlogn으로 sorting하고 맨 앞에꺼 떼오는거 보다

JAVA code에서 findAll하고 sequential search하는게 n으로 더 빠를 것 같다.
