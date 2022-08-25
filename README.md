# 🛳 항 해 인 사 이 드 [클론 코딩]

# 클론코딩 **"항해인사이드"**

### ✨ [FrontEnd](https://github.com/HANGHAE-INSIDE-BE-FE/HANGHAE-INSIDE-BE) : [박수원](https://github.com/kksltv123), [권용준](https://github.com/donamgunner), [고호성](https://github.com/alestorm001)

### ✨ [BackEnd](https://github.com/HANGHAE-INSIDE-BE-FE/HANGHAE-INSIDE-BE) : [최준우](https://github.com/gitpher), [강지영](https://github.com/picjoy), [김재영](https://github.com/KORJaeyoungKim)

# 🚢 항 해 인 사 이 드 *(디시인사이드 갤러리 클론코딩)*

### 국내 최대 인터넷 커뮤니티 포털이자 인터넷 트렌드의 중심, ***디시인사이드***

### 실시간 베스트 등의 갤러리 커뮤니티 서비스를 제공합니다.

- **`공유할 게시글 작성`** **`게시글 댓글 달기`** **`마음에 드는 게시물을 좋아요` `베스트 게시글`**

## **🍰 [항해인사이드 서비스 바로가기](https://hanghae99-inside.vercel.app/main)**

## **🎬 [서비스 시연 영상](https://www.youtube.com/watch?v=iOP1HuuRAWA)**

## 🌠 사용 기술

<div>
<img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white">
<img src="https://img.shields.io/badge/javascript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black">
<img src="https://img.shields.io/badge/react-0769AD?style=for-the-badge&logo=react&logoColor=white">
<img src="https://img.shields.io/badge/html-E34F26?style=for-the-badge&logo=html5&logoColor=white">
<img src="https://img.shields.io/badge/css-1572B6?style=for-the-badge&logo=css3&logoColor=white">
<br>
<img src="https://img.shields.io/badge/ubuntu-FCC624?style=for-the-badge&logo=ubuntu&logoColor=black">
<img src="https://img.shields.io/badge/aws-232F3E?style=for-the-badge&logo=amazonaws&logoColor=white">
<img src="https://img.shields.io/badge/spring-F80000?style=for-the-badge&logo=spring&logoColor=white">
<img src="https://img.shields.io/badge/Springboot-4FC08D?style=for-the-badge&logo=springboot&logoColor=white">
<img src="https://img.shields.io/badge/springsecurity-7952B3?style=for-the-badge&logo=springsecurity&logoColor=white">
<img src="https://img.shields.io/badge/mysql-61DAFB?style=for-the-badge&logo=mysql&logoColor=white">
</div> <br>

### 1. 회 원 가 입
<details>
<summary> Join Membership </summary>
<div markdown="1">
<br>
아이디를 이메일로 하고 닉네임을 따로 받아서 회원가입하는 것으로 구현
<br><br>
아이디와 비밀번호 양식 체크는 따로 서버에 요청이 들어오도록 구현
</div>
</details>

### **2. 로 그 인**
<details>
<summary> LogIn </summary>
<div markdown="1">
<br>
로그인 시에 토큰이 자동적으로 헤더에 들어오도록 기능 구현
<br><br>
토큰 만료 시간은 30분으로 구현
</div>
</details>

### **3. 메 인 페 이 지**
<details>
<summary> Main </summary>
<div markdown="1">
<br>
포스팅된 게시글 리스트를 최신순으로 출력
<br><br>
른쪽 배너에 각 페이지에 따른 개념글 표시
</div>
</details>

### **4. 개 념 글( 베 스 트 글 )**
<details>
<summary> Best </summary>
<div markdown="1">
<br>
추천 수가 10개가 넘는 게시물 중 상위 게시글 10개 리스트 출력
<br>
</div>
</details>

### **5. 게 시 글**
<details>
<summary> CreatePost </summary>
<div markdown="1">
<br>
로그인이 된 회원만 게시글을 작성할 수 있도록 구현
<br><br>
사진 유무를 전체 게시물 페이지에서 불 수 있으며, 사진을 유무에 관계없이 게시글 생성 가능
</div>
</details>

### 6. 댓글/대댓글
<details>
<summary> CRUD </summary>
<div markdown="1">
<br>
별도의 회원가입을 하지 않고도 nickname 값과 password 값 입력만으로 댓글/대댓글 작성이 가능하도록 구현
<br><br>
전체적으로 request값을 바탕으로 올바르지 않은 요청일 경우 (ex : “존재하지 않는 댓글입니다.”)등의 예외문구 출력 처리<br>
createComment(댓글 생성) / getComment(댓글 조회) 메소드 내 createdAt(생성시간)과 modifiedAt(최종수정시간) `yyyy-MM-dd HH:mm` 형태로 패턴 변환하여 저장 및 출력
</div>
</details>

### 7. 개념추천(좋아요)/비추천(싫어요)
<details>
<summary>  Heart/UnHeart </summary>
<div markdown="1">
<br>
로그인을 하여야만 개념추천/비추천이 가능하도록 구현
<br><br>
개념추천/비추천 모두 1인 1게시글 당 1번씩만 가능하며 중복 요청 시 (ex : “이미 추천을 했습니다.”)등의 예외문구 출력 처리<br>
개념추천/비추천 모두 취소는 불가능하도록 구현<br>
Header를 통해 들어오는 Token 및 request 값을 바탕으로 올바르지 않은 요청일 경우 (ex : “로그인이 필요합니다.”) 등의 예외문구 출력 처리
</div>
</details>

### 8. 마이 페이지
<details>
<summary> Mypage </summary>
<div markdown="1">
<br>
로그인 된 회원이 작성한 게시글 표시
<br>
</div>
</details>

## **🔨 API 설계**



[API 설계 한눈에 보기](https://www.notion.so/0bba5380d0f941a68618da529d068091)



## 🔧 ERD 설계


![HangHaeInside](https://user-images.githubusercontent.com/108355379/186662819-5a5954e3-879c-43f2-b977-361291095177.png)



## **🐛 트러블 슈팅**



[트러블 슈팅 한눈에 보기](https://www.notion.so/3ba6b1c3b2bb409dbe9fc26562616120)



## 📝 Git Commit Message Rule

### **Format: [ 수정자 ] < type > commit message**

- **feat** : 새로운 기능에 대한 커밋
- **fix** : 버그 수정에 대한 커밋
- **build** : 빌드 관련 파일 수정에 대한 커밋
- **chore** : 그 외 자잘한 수정에 대한 커밋
- **ci** : CI관련 설정 수정에 대한 커밋
- **cd** : CD관련 설정 수정에 대한 커밋
- **docs** : 문서 수정에 대한 커밋
- **style** : 코드 스타일 혹은 포맷 등에 관 한 커밋
- **refactor** : 코드 리팩토링에 대한 커밋
- **test** : 테스트 코드 수정에 대한 커밋



## 💗🌟 8조 잘한 점

## ✨🧩 **깔끔한 Git 관리**
![깃 로그 PNG](https://user-images.githubusercontent.com/108355379/186662591-a002e23e-d589-4357-9968-bcfe38e0aa99.png)
![Untitled (1)](https://user-images.githubusercontent.com/108355379/186662674-da1a7d52-56f7-493b-a7e2-5a4e6015e84d.png)

### **→ Rebase를*사용하고 Git Commit 양식을 지킴으로써 Git을 깔끔하게 관리했다.***

## ✨🧩 **Post와 Member Entity의 불필요한 의존 관계 제거**
![Untitled (2)](https://user-images.githubusercontent.com/108355379/186662730-ef221132-050c-4112-a47b-ff9471064fae.png)
![createdById](https://user-images.githubusercontent.com/108355379/186662763-ed605851-2018-4698-8254-8df9bc996ee7.png)

### **→ *Post Entity에 Member 객체를 통째로 넣지 않고 createdById를 만들어***

### ***작성자의 닉네임을 넣어주어서 Entity간의 결합도를 줄였다.***
