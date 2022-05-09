# Mission 목표
* Spring Security를 활용하여 로그인, 회원가입 구현
* CommunityUserDetailsService 를 UserDetailsService의 구현체로 선언해 정의
* AreaEntity는 더미 데이터를 사용
## 미션 스크린샷
### 더미 데이터
![image](https://user-images.githubusercontent.com/80913353/165720189-62632066-5302-4fcc-a6df-35d46991958a.png)
### 회원 가입
![image](https://user-images.githubusercontent.com/80913353/165720465-47cbfa04-7499-49cc-b0ac-cfa8d2d59193.png)
### 로그인
![image](https://user-images.githubusercontent.com/80913353/165720676-e775cd71-dde9-48fb-a488-33d1758a19f3.png)


// Spring Security에서 POST는 기본적으로 막혀있다고 함.
// configure()에서 .csrf().disable() 사용하면 가능

// check box는 선택하지 않으면 null 값이 넘어옴
// default value = false 선언

// PasswordEncoder로 encoding 필수
