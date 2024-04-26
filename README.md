# TheCommerce_Toy

## 프로젝트 진행 의도

- TheCommerce 기업의 과제 풀이

|    Name     | Info     |
|:-----------:|:---------|
| Spring Boot | 2.7.18   |
|    java     | 1.8      |
| PostgreSQL  | 15       |
|     IDE     | Intellij |

## 적용 기술

- QueryDsl
- Swagger

## 프로젝트 진행 기간
- 2024.04.23 - 2024.04.26
---

# 프로젝트 실행 방법

- # **중요 사항**
- IntelliJ로 프로젝트 실행 시 아래와 같은 설정으로 진행하여야 한다.

- 설정 -> 빌드, 실행, 배포 -> 빌드 도구 -> Gradle
- Settings -> Build, Execution, Deployment -> Build Tools -> Gradle
  ![image](https://github.com/MyohanMyolang/TheCommerce_Toy/assets/85920191/a1cfc7c0-c0a0-4284-b8d4-329c4407923a)



- resources 폴더에 .env 파일 생성 후 아래와 같은 방식을 통하여 DB 정보를 기입 후 실행.
- 또는 환경 변수를 주는 방법으로 실행

```.env
POSTGRES_URL = DB주소
POSTGRES_ID = DB_ID
POSTGRES_PW = DB_PASSWORD
```

- local 환경 실행 법
  - application-db-local.yaml 파일을 resources에 작성 후 작업
```yaml
# local
spring:
  config:
    activate:
      on-profile: "db-local"
  datasource:
    driver-class-name: [Local Db driver]
    url: [Local Db Url]
    username: [Local Db UserName]
    password: [Local Db Password]
```
---

# 개발 사항

## QueryDsl 적용 이유

- Enum을 이용하여 동적으로 정렬 기준을 정해야 하는데 문자열을 통한 관리보다는 객체를 다룸으로써 타입 안정성과 추후 확장성을 얻기 위하여 QueryDsl을 적용하였다.

## 불변성을 지키기 위하여 final 사용

- Member Model에 final을 사용하여 불변성을 지켜 휴먼 에러를 방지하고 그럼에도 불구하고 휴먼 에러가 날 경우 추적의 편의성을 높이기 위하여 적용

### 트러블 슈팅

불변성을 지키기 위하여 final을 사용하였으나 일부 값 업데이트를 할 수가 없었다. </br>
그래서 객체를 Clone하는 법을 찾던 도중 @Builder(toBuilder = true)가 원하던 형태로 사용을 할 수 있었기에 채택하였다. </br>

```java
Member updatedMember = member.toBuilder()
  .nickname(request.getNickname().orElse(member.getNickname()))
  .name(request.getName().orElse(member.getName()))
  .phoneNumber(request.getPhoneNumber().orElse(member.getPhoneNumber()))
  .email(request.getEmail().orElse(member.getEmail()))
  .password(ifPresentPasswordThenEncode.apply(request.getPassword()))
  .build();
```

위와 같이 사용을 하게 되는데 함수 체이닝에 걸려있지 않은 경우 원본 객체의 값을 그대로 유지하고, 수정되는 부분만 update를 할 수 있었다. </br>
또한 DTO Field에 Optional을 줌으로써 하나의 DTO 객체만으로 일부 값만 업데이트 요청 데이터가 들어왔을 때도 간단히 처리할 수 있었으며 관리 포인트도 하나로 가져갈 수 있게 되었다.


## AuthService 테스트 코드에 mock을 사용하지 않고 객체를 직접 만들어 코드 사용

- mock 라이브러리의 스펙 변경으로 인하여 테스트가 깨지지 않도록 하기 위하여

---
