# 📝 Java Blog — JSP/Servlet 기반 개인 블로그

> 프레임워크 없이 **순수 Java Servlet + JSP**만으로 Spring MVC의 핵심 구조(Front Controller, 계층형 아키텍처, DI 유사 구성)를 직접 구현한 개인 블로그 프로젝트입니다.

---

## 📌 1. 프로젝트 개요

이 프로젝트는 게시물 작성/조회/수정/삭제, 댓글, 회원 가입/로그인, 계정 찾기(이메일 임시 비밀번호 발송) 등 블로그 서비스의 핵심 기능을 모두 갖춘 **풀스택 웹 애플리케이션**입니다.

Spring 같은 프레임워크를 사용하지 않고, 다음을 **바닥부터 직접 구현**한 것이 가장 큰 특징입니다.

- 🚪 **Front Controller 패턴** — 단일 `DispatcherServlet`이 `/s/*` 경로의 모든 요청을 받아 URL을 파싱하여 컨트롤러/액션으로 라우팅
- 🏗️ **계층형 아키텍처** — Controller → Service → DAO → DB 로 이어지는 명확한 책임 분리
- 🔒 **SecSql** — SQL Injection을 방지하는 자체 제작 쿼리 빌더 (`PreparedStatement` 래핑)
- 🗃️ **Attr 시스템** — EAV(Entity-Attribute-Value) 방식의 범용 부가정보 저장소 (임시 비밀번호 플래그, 인증코드 등)
- 🛡️ **Guard(인터셉터) 로직** — 액션 실행 전 로그인/로그아웃 상태를 검사하는 공통 관문

즉, "프레임워크가 내부에서 어떻게 동작하는가"를 학습하고 증명하는 성격의 프로젝트이자, 실제 배포(`ksh.blog.coding.family`)를 염두에 둔 실전형 블로그입니다.

---

## ✨ 2. 주요 기능

### 📰 게시물 (Article)

| 기능 | 액션 | 설명 |
|---|---|---|
| 목록 조회 | `article/list` | 페이징(10개/페이지), 카테고리 필터, 제목 검색 지원 |
| 상세 조회 | `article/detail` | 조회수(hit) 자동 증가, 댓글 목록 동시 렌더링 |
| 작성 | `article/write`, `doWrite` | **Toast UI Editor** 기반 마크다운 작성 (🔐 로그인 필요) |
| 수정 | `article/modify`, `doModify` | 작성자 본인만 가능 (권한 검사) |
| 삭제 | `article/doDelete` | 작성자 본인만 가능 (권한 검사) |

- 카테고리: `IT/일반`, `IT/알고리즘`, `IT/프론트엔드`, `IT/백엔드`, `디자인/피그마`, `일상/일반`
- 본문은 마크다운으로 저장되며, 조회 시 highlight.js로 코드 하이라이팅 처리
- `getBodyForXTemplate()` — 본문 내 `script` 문자열을 치환하여 **XSS 방어**

### 💬 댓글 (ArticleReply)

- 댓글 작성 / 수정 / 삭제 (모두 로그인 및 본인 확인 필요)
- 작업 완료 후 `lastWorkArticleReplyId` 쿼리 파라미터로 **마지막 작업한 댓글 위치로 복귀**

### 👤 회원 (Member)

| 기능 | 액션 | 설명 |
|---|---|---|
| 회원가입 | `member/join`, `doJoin` | 아이디·닉네임·이메일 중복 검사 후 가입, **환영 메일 자동 발송** |
| 아이디 중복확인 | `member/getLoginIdDup` | **JSON 응답**을 반환하는 Ajax 엔드포인트 |
| 로그인/로그아웃 | `member/login`, `doLogin`, `doLogout` | 세션(`loginedMemberId`) 기반 인증 |
| 아이디 찾기 | `member/doFindLoginId` | 이름 + 이메일 조합으로 조회 |
| 비밀번호 찾기 | `member/doFindLoginPw` | **임시 비밀번호를 생성해 Gmail SMTP로 발송** |
| 개인정보 수정 | `member/modifyPrivate` | 비밀번호 재확인 → **UUID 인증코드** 발급 후 수정 허용 |

- 🔑 비밀번호는 **SHA-256 해시**로 저장 (클라이언트에서 `loginPwReal`로 해시 전달)
- ⏰ 임시 비밀번호로 로그인하면 "비밀번호를 변경해주세요" 알림 표시 (`attr` 테이블의 `useTempPassword` 플래그 활용)

### 🏠 홈 / 기타

- `home/main` — 메인 페이지, `home/aboutMe` — 소개 페이지
- `test/*` — DB 입출력, 메일 발송, Attr 시스템 테스트용 컨트롤러

---

## 🛠️ 3. 기술 스택

### Backend
| 기술 | 용도 |
|---|---|
| **Java (Servlet API 4.0)** | 핵심 언어 및 웹 계층 (`@WebServlet`) |
| **JSP + JSTL 1.2** | 서버사이드 뷰 렌더링 |
| **MySQL** + `mysql-connector-java 8.0.20` | 데이터 저장소 (JDBC 직접 사용) |
| **Lombok 1.18.12** | DTO 보일러플레이트 제거 (`@Data`) |
| **JavaMail 1.4.7** | Gmail SMTP(587, STARTTLS) 메일 발송 |
| **Apache Tomcat** | 서블릿 컨테이너 (WebContent 배포 구조) |

### Frontend (CDN)
| 기술 | 용도 |
|---|---|
| **Toast UI Editor** (+ code-syntax-highlight 플러그인) | 마크다운 에디터/뷰어 |
| **jQuery 3.5.1** | DOM 조작 및 Ajax |
| **highlight.js 10.1.1** | 코드 블록 문법 강조 |
| **Font Awesome 5.13.1** | 아이콘 |
| **Google Fonts** (Noto Sans KR, Roboto) | 웹 폰트 |

---

## 📂 4. 디렉토리 및 파일 구조

```
java-blog/
├── 📄 db.sql                              # DB 스키마 + 초기 데이터 (마이그레이션 이력 포함)
├── 📄 README.md
│
├── 📁 src/com/sbs/java/blog/              # ☕ Java 소스
│   ├── 📁 servlet/
│   │   └── DispatcherServlet.java         # 🚪 Front Controller (/s/* 전체 요청 수신)
│   ├── 📁 app/
│   │   └── App.java                       # ⚙️ DB 연결 관리 + URL 파싱 + 컨트롤러 라우팅
│   ├── 📁 controller/
│   │   ├── Controller.java                # 🧩 추상 부모 (beforeAction / doGuard / executeAction)
│   │   ├── ArticleController.java         # 게시물·댓글 CRUD 액션
│   │   ├── MemberController.java          # 회원가입·로그인·계정찾기·개인정보수정
│   │   ├── HomeController.java            # 메인·소개 페이지
│   │   └── TestController.java            # 개발용 테스트 액션
│   ├── 📁 service/
│   │   ├── Service.java                   # 추상 부모
│   │   ├── ArticleService.java            # 게시물/댓글 비즈니스 로직 + 권한 검사
│   │   ├── MemberService.java             # 회원 로직 + 임시비밀번호·인증코드 발급
│   │   ├── AttrService.java               # EAV 부가정보 관리 (name 문자열 파싱)
│   │   └── MailService.java               # 메일 발송 파사드
│   ├── 📁 dao/
│   │   ├── Dao.java                       # 추상 부모
│   │   ├── ArticleDao.java                # 게시물/댓글/카테고리 SQL
│   │   ├── MemberDao.java                 # 회원 SQL
│   │   └── AttrDao.java                   # attr 테이블 SQL
│   ├── 📁 dto/
│   │   ├── Dto.java                       # 공통 부모 (id, regDate, updateDate, extra Map)
│   │   ├── Article.java / ArticleReply.java
│   │   ├── Member.java / CateItem.java / Attr.java
│   ├── 📁 util/
│   │   ├── SecSql.java                    # 🔒 PreparedStatement 기반 안전 쿼리 빌더
│   │   ├── DBUtil.java                    # selectRow / selectRows / insert / update 헬퍼
│   │   ├── Util.java                      # 파라미터 검증, SHA-256, URI 조작, 메일 발송
│   │   └── MailAuth.java                  # SMTP 인증 정보
│   ├── 📁 config/
│   │   └── Config.java                    # 사이트명, 메일 발신자 설정
│   └── 📁 exception/
│       └── SQLErrorException.java         # SQL 예외 래핑
│
└── 📁 WebContent/                         # 🌐 웹 리소스 (Tomcat 배포 루트)
    ├── 📁 WEB-INF/
    │   ├── web.xml.sample                 # Gmail 앱 비밀번호 설정 템플릿
    │   └── lib/                           # jstl, mysql-connector, mail, lombok JAR
    ├── 📁 jsp/
    │   ├── 📁 home/      → main.jsp, aboutMe.jsp
    │   ├── 📁 article/   → list.jsp, detail.jsp, write.jsp, modify.jsp, modifyReply.jsp
    │   ├── 📁 member/    → join.jsp, login.jsp, findAccount.jsp,
    │   │                   passwordForPrivate.jsp, modifyPrivate.jsp
    │   ├── 📁 common/    → data.jsp (alert/redirect 처리 공통 뷰)
    │   └── 📁 part/      → head.jspf, foot.jspf, toastUiEditor.jspf (공통 조각)
    └── 📁 resource/
        ├── css/  → common.css, home/main.css
        └── js/   → common.js, home/main.js
```

### 🗄️ DB 스키마 (`db.sql`)

| 테이블 | 설명 |
|---|---|
| `article` | 게시물 (제목, 본문, 카테고리, 작성자, 조회수, 노출 여부) |
| `articleReply` | 댓글 (게시물 번호, 작성자, 본문) |
| `member` | 회원 (loginId·nickname UNIQUE, 비밀번호 SHA-256, level) |
| `cateItem` | 게시물 카테고리 |
| `attr` | 🗃️ EAV 부가정보 저장소 — `(relTypeCode, relId, typeCode, type2Code)` 복합 UNIQUE 인덱스 |

---

## ⚙️ 5. 핵심 비즈니스 로직과 동작 원리

### 5-1. 요청 처리 흐름 (Front Controller 패턴)

```
브라우저 요청: GET /s/article/detail?id=3
        │
        ▼
┌─ DispatcherServlet (@WebServlet("/s/*")) ─────────────────┐
│  UTF-8 인코딩 설정 → new App(req, resp).start()            │
└────────────────────────────────────────────────────────────┘
        ▼
┌─ App.start() ──────────────────────────────────────────────┐
│  ① ServletContext에서 Gmail 설정 로드                       │
│  ② MySQL JDBC 드라이버 로딩 + 커넥션 생성                    │
│     (spring.profiles.active 로 개발/운영 DB 분기)            │
│  ③ URI 파싱: "article/detail" → 컨트롤러명 + 액션명           │
│  ④ switch로 해당 Controller 생성 (커넥션 주입)               │
│  ⑤ finally에서 커넥션 반드시 close (요청당 1커넥션)           │
└────────────────────────────────────────────────────────────┘
        ▼
┌─ Controller.executeAction() ───────────────────────────────┐
│  beforeAction() → doGuard() → doAction() → afterAction()   │
└────────────────────────────────────────────────────────────┘
        ▼
액션 결과 문자열 해석 (App.route)
  ├─ "article/detail.jsp"  → /jsp/ 하위 JSP로 forward (뷰 렌더링)
  ├─ "html:<script>..."    → HTML/JS 조각 직접 출력 (alert + redirect)
  └─ "json:{...}"          → JSON 응답 (Ajax용, Content-Type 변경)
```

컨트롤러 액션의 **반환 문자열 접두사가 응답 방식을 결정**하는 것이 이 프로젝트 라우팅의 핵심 규약입니다.

### 5-2. 공통 전처리와 가드 (`Controller.java`)

모든 액션 실행 전 `beforeAction()`이 다음을 수행합니다.

- 카테고리 목록(`cateItems`)을 조회하여 모든 페이지의 내비게이션에 공급
- 세션의 `loginedMemberId`로 로그인 상태·회원 정보를 복원하여 request 속성에 주입
- 현재 URI를 인코딩하여 **로그인 후 원래 페이지로 복귀**(`afterLoginRedirectUri`)할 수 있도록 준비

이어서 `doGuard()`가 Spring의 인터셉터처럼 동작합니다.

- 🔐 **로그인 필수 액션** (글쓰기/수정/삭제, 로그아웃 등): 비로그인 시 로그인 페이지로 강제 이동
- 🚫 **로그아웃 필수 액션** (로그인/가입/계정찾기 페이지): 로그인 상태면 접근 차단

### 5-3. SQL Injection 방어 — `SecSql` + `DBUtil`

문자열 결합 대신 SQL 조각과 파라미터를 함께 쌓는 빌더를 사용합니다.

```java
SecSql sql = new SecSql();
sql.append("SELECT A.*, M.nickname AS extra__writer");
sql.append("FROM article AS A INNER JOIN member AS M ON A.memberId = M.id");
sql.append("WHERE A.displayStatus = 1");
if (cateItemId != 0) {
    sql.append("AND A.cateItemId = ?", cateItemId);   // ← 값은 별도 리스트에 보관
}
sql.append("LIMIT ?, ?", limitFrom, itemsInAPage);
```

`getPreparedStatement()`가 모든 `?`를 `PreparedStatement` 바인딩으로 변환하므로 **동적 조건(카테고리 필터, 검색어)이 있어도 SQL Injection이 원천 차단**됩니다. `DBUtil`은 결과를 `Map<String, Object>`로 변환하고, DTO 생성자가 이를 객체로 매핑합니다. 이때 `extra__writer` 같은 **`extra__` 접두사 컬럼은 DTO의 `extra` Map에 자동 수납**되어, JOIN으로 가져온 부가 데이터를 스키마 변경 없이 담을 수 있습니다.

### 5-4. EAV 부가정보 시스템 — `attr` 테이블

회원/게시물에 임의의 속성을 붙일 수 있는 범용 key-value 저장소입니다.

```
"member__12__extra__useTempPassword" = "1"
   │       │      │          └ type2Code (속성명)
   │       │      └ typeCode (속성 그룹)
   │       └ relId (대상 PK)
   └ relTypeCode (대상 테이블)
```

`AttrService`가 `__` 구분자로 이름을 파싱하여 4개 컬럼으로 저장하며, 복합 UNIQUE 인덱스로 중복을 방지합니다. 이 시스템 위에 **임시 비밀번호 사용 플래그**와 **개인정보 수정 인증코드**가 구현되어 있습니다.

### 5-5. 계정 보안 플로우

**🔑 비밀번호 찾기 (임시 비밀번호 발급)**

1. 아이디 + 이메일 일치 확인 (`getMemberByLoginId`)
2. 6자리 랜덤 임시 비밀번호 생성 → **SHA-256 해시로 DB 저장**, 원문은 메일로만 발송
3. `attr`에 `useTempPassword = 1` 기록
4. 임시 비밀번호로 로그인하면 "비밀번호를 변경해주세요" 경고 표시
5. 비밀번호 변경 시 플래그 제거

**🛡️ 개인정보 수정 (2단계 확인)**

1. `passwordForPrivate`에서 현재 비밀번호 재입력
2. 일치하면 **UUID 인증코드**를 생성해 `attr`에 저장하고 수정 페이지로 전달
3. 수정 페이지/저장 액션 진입 시마다 인증코드를 DB 값과 대조 → URL 직접 접근 차단

### 5-6. 권한 검사 패턴 (`resultCode` 규약)

게시물/댓글의 수정·삭제 가능 여부는 `Map<String, Object>` 기반의 공통 규약으로 반환됩니다.

- `S-1` (성공) / `F-1` (대상 없음) / `F-2` (권한 없음) + 사용자 메시지(`msg`)
- 컨트롤러는 `Util.isSuccess()`로 판정 후 실패 시 alert 출력
- 목록/상세 조회 시에도 동일 검사를 수행해 `extra` Map에 `modifyAvailable`/`deleteAvailable`을 담아 **JSP에서 버튼 노출 여부를 결정** — 서버 검증과 UI 표시가 같은 로직을 공유합니다.

---

## 🚀 실행 방법

1. **DB 준비** — MySQL에서 `db.sql`을 순서대로 실행 (기본 DB명: `test`, 기본 계정: `admin`/`admin`)
2. **DB 접속정보 설정** — 환경변수로 지정 (미지정 시 개발용 기본값 `localhost` / `root` / 빈 비밀번호 사용)

   | 환경변수 | 설명 | 예시 |
   |---|---|---|
   | `BLOG_DB_URI` | JDBC 접속 URI | `jdbc:mysql://localhost:3306/test?serverTimezone=Asia/Seoul` |
   | `BLOG_DB_ID` | DB 계정 아이디 | `blog_user` |
   | `BLOG_DB_PW` | DB 계정 비밀번호 | `********` |

3. **메일 설정** — `WebContent/WEB-INF/web.xml.sample`을 `web.xml`로 복사 후 Gmail **앱 비밀번호** 입력
4. **배포** — Eclipse/IntelliJ에서 Tomcat 서버에 프로젝트를 올려 실행 (Tomcat 실행 환경에 위 환경변수 등록)
5. **접속** — `http://localhost:8080/{컨텍스트경로}/s/home/main`

> 💡 운영 서버는 JVM 옵션 `-Dspring.profiles.active=production` 지정 시 운영 DB(`blog_db`)로 자동 전환됩니다.
