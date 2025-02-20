# Spring Boot Practice
스프링 부트 연습 프로젝트  
  
[1. 프로젝트 소개](#프로젝트-소개)  
[2. 화면 구성](#화면-구성)  
[3. API 명세](#api-명세)  
[4. 아키텍쳐](#아키텍쳐)  
[5. REVIEW](#프로젝트-review)  
  

### 프로젝트 소개
기초적인 웹(벡엔드)개발을 연습하기 위해 생성된 프로젝트입니다.

#### 개발 기간
2025.02. ~ 2025.02.20

#### 개발 환경
- os: macOS(Sequoia)
- envirionment: IntelliJ IDEA, GITHUB
- framework: Spring Boot 3.4.2 (OpenJDK-21)
- database: MySQL 9.2.0

#### 주요 기능
- 로그인, 로그아웃
- 회원가입
- 게시글 작성(생성 및 수정), 게시글 조회, 게시글 삭제


---

### 화면 구성

#### 로그인 화면
<img src="https://github.com/user-attachments/assets/b91e917b-1c4d-4733-b022-fd426c2ce347" width="700" height="400"/>

#### 회원가입 화면
<img src="https://github.com/user-attachments/assets/bd787374-1c3a-4fa8-831d-84a41ad7c29c" width="700" height="400"/>

#### 공지사항 조회 화면
<img src="https://github.com/user-attachments/assets/c5a55eec-5244-4f64-b83a-3ccbb41f300d" width="700" height="400"/>

#### 공지사항 작성 화면
<img src="https://github.com/user-attachments/assets/72120849-04be-427b-b564-2b02f762663c" width="700" height="400"/>

#### 공지사항 수정 및 삭제 화면
<img src="https://github.com/user-attachments/assets/4c20abcd-8c38-4e7d-be10-00ba8c779fc9" width="500" height="300"/>
<img src="https://github.com/user-attachments/assets/2b01b9f4-1e31-4e01-84d4-faa1b4f199ad" width="500" height="300"/>


---

### API 명세

#### 기본 정보
- **Base URL**: `http://localhost:8080`
- **API 문서 URL**: `http://localhost:8080/swagger-ui/index.html#/`
- **API 버전**: 1.0 (OAS 3.0)

#### 엔드포인트 목록(수정 보완 필요)

| 메서드 | URL | 설명 | 요청 파라미터 | 요청 바디 | 응답 |
|--------|------------------|---------------------|------------------|------------|------|
| **DELETE** | `/menu/delete/{idx}` | 특정 메뉴 항목 삭제 | `idx` (integer, path, required) | 없음 | `200 OK` |
| **GET** | `/menu/{idx}` | 특정 메뉴 항목 조회 | `idx` (integer, path, required) | 없음 | `200 OK` + JSON 응답 |
| **GET** | `/menu/all` | 모든 메뉴 항목 조회 | 없음 | 없음 | `200 OK` |
| **POST** | `/menu/add` | 새로운 메뉴 항목 추가 | 없음 | JSON 바디 | `200 OK` |
| **PUT** | `/menu/update/{idx}` | 특정 메뉴 항목 수정 | `idx` (integer, path, required) | JSON 바디 | `200 OK` |
| **PUT** | `/menu/count/{idx}` | 특정 메뉴 조회수 증가 | `idx` (integer, path, required) | 없음 | `200 OK` |

#### 요청 바디 예시
##### 메뉴 추가 & 수정 요청 바디 (JSON)
```json
{
  "idx": 0,
  "memID": "string",
  "title": "string",
  "content": "string",
  "writer": "string",
  "indate": "string",
  "count": 0
}
```

---

### 아키텍쳐

#### 시퀀스 다이어그램
작성 필요

#### 디렉토리 구조
```
├── main
│   ├── java
│   │   └── com
│   │       └── example
│   │           └── spring_project1
│   │               ├── SpringProject1Application.java
│   │               ├── config
│   │               │   ├── SecurityConfig.java
│   │               │   └── SwaggerConfig.java
│   │               ├── controller
│   │               │   ├── MenuRestController.java
│   │               │   ├── PageController.java
│   │               │   └── UserController.java
│   │               ├── entity
│   │               │   ├── CustomUser.java
│   │               │   ├── Menu.java
│   │               │   ├── Role.java
│   │               │   └── User.java
│   │               ├── mapper
│   │               │   ├── MenuRestMapper.java
│   │               │   └── UserMapper.java
│   │               └── service
│   │                   ├── MenuRestService.java
│   │                   ├── UserDetailsServicelmpl.java
│   │                   └── UserService.java
│   ├── resources
│   │   ├── application.properties
│   │   ├── static
│   │   └── templates
│   └── webapp
│       ├── WEB-INF
│       │   └── views
│       │       ├── common
│       │       │   ├── footer.jsp
│       │       │   └── header.jsp
│       │       ├── index.jsp
│       │       ├── login
│       │       │   └── index.jsp
│       │       ├── noticeAdd
│       │       │   └── index.jsp
│       │       ├── noticeCheck
│       │       │   └── index.jsp
│       │       ├── noticeModify
│       │       │   └── index.jsp
│       │       └── register
│       │           └── index.jsp
│       └── resources
│           ├── css
│           │   ├── common
│           │   │   ├── footer.css
│           │   │   └── header.css
│           │   ├── login
│           │   │   └── style.css
│           │   ├── noticeAdd
│           │   │   └── style.css
│           │   ├── noticeCheck
│           │   │   └── style.css
│           │   ├── noticeModify
│           │   │   └── style.css
│           │   ├── register
│           │   │   └── style.css
│           │   └── style.css
│           └── js
│               ├── noticeAdd
│               │   └── script.js
│               ├── noticeCheck
│               │   └── script.js
│               ├── noticeModify
│               │   └── script.js
│               └── script.js
└── test
    └── java
        └── com
            └── example
                └── spring_project1
                    └── SpringProject1ApplicationTests.java
```

#### 데이터베이스 구조
<img src="https://github.com/user-attachments/assets/1cd21ba3-e6f6-4738-bda3-bd13f0275d2c" width="350" height="200"/>

---

### 프로젝트 REVIEW
spring boot를 이용한 첫 웹 프로젝트를 진행하면서, 벡엔드와 데이터베이스, 프론트엔드가 어떠한 방식으로 상호작용하는지 이해하였다.
특히, Spring Security와 csrf토큰 방식을 이용한 로그인, 로그아웃, 회원가입, 세션 기능들을 활용할 수 있어, 앞으로의 개발에 도움을 주는 경험이었다.
다음 프로젝트에는 csrf 대신 jwt토큰을 이용한 인증 및 권한부여 방식을 적용하는 것을 고려해보는게 좋을 것 같다.





