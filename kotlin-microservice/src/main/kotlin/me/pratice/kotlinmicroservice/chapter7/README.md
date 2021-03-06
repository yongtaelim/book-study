# 모범 사례
모범사례를 정리해보자
- 코틀린 관용구
- 스프링 컨텍스트
- 계층화
- 테스팅
- 지속적인 통합
- 지속적인 배포

## 코틀린 관용구
### 타입추론
- types

### 표현식
- loolu1

### 기본 매개 변수
```kotlin
fun compute(number1: Int, number2: Int = 2, number3: Int = 3) = number1 * number2 * number3
```

### 람다
- lambda

### 스프링 컨텍스트 관리
- Spring Application Context는 Bean이 사용되도록 참조되는 곳이며, 올바르게 관리하는 것은 간단한 작업이 아니다. 수십개의 빈이 있을 때 어디에서 그리고 어떻게 접근할 것인가가 중요!
                  
#### 생성자 주입
- AccountService

#### 명시적 컨텍스트 설정
@Component, @Service를 사용해 스프링 부트 애플리케이션이 시작할 때 컴포넌트 스캔으로 스프링 컨텍스트에 추가할 빈을 선언했다.
Configuration 클래스를 사용해 빈을 명시적으로 선언해보자
- ContextConfiguration
application 클래스와 ContextConfiguration 클래스를 별도의 패키지로 옮길 수 있다. 이렇게 하려면 컨텍스트에서 필요한 것을 정확하게 알려주는 명시적 컨텍스트 설정을 해야한다.
  
#### 서비스 분리
- interface를 두자

### 애플리케이션의 계층화
- classes1, classes2, classes3

- model : 데이터 클래스와 같은 도메인 모델 객체
- services : 도메인의 비즈니스 로직을 사용하는 서비스, 구현은 분리할 수 있다.
- controller : 모델을 노출하고 서비스를 사용하는 컨트롤러

### 테스트
#### 단위 테스트
- AccountServiceTest, CustomerServiceTest 처럼 클래스명에 Test가 붙는다.
- 격리된 상태에서 다른 장치와는 독립적인 테스트 진행
- 즉각적인 피드백을 제공 받아야한다.

#### 통합 테스트
- CustomerControllerIntegrationTest, CustomerControllerIT, com.example.services.test.integration 패키지 하위에 생성
- 다양한 컴포넌트가 제대로 동작하는지 테스트

#### E2E 테스트
- End to End
- 전체 기능이 최종 사용자의 관점에서 잘 동작하는 것을 입증.

단위 < 통합 < E2E

### TDD
- 테스트 뿐만 아니라 마이크로서비스의 설계를 더 효율적으로 구현하는 데도 도움이 된다.
- TDD를 수행하기 위한 전반적인 아이디어는 아직 구현코드가 없기 때문에 실패하는 테스트 먼저 작성한다.
- 통과를 위한 최소 코드 구현
- 코드를 리팩터링해 코드를 개선하고 테스트를 다시 실행해 코드가 다시 작동하는지 확인 및 수정한다.
- 적색 - 녹색 - 수정

### BDD
- / 행위/행동 주도 개발
- 소프트웨어 개발에서 개발 팀과 관리 팀이 공유할 수 있는 도구와 프로세스를 제공하기 위해 도메인 주도 설계와 객체 지향 분석 및 설계의 아이디어를 가지고 TDDdptj skdhs thvmxmdnpdj roqkf vmfhtptmek.

- 도메인 전문가를 포함해 소프트웨어 요구 사항을 작성
```
Story : API에서 고객 가져오기

API의 사용자로서 고객을 가져오기 위해 나는 그것을 질의하고 싶다.

시나리오1 : 존재하는 고객 가져오기 
고객으로부터 ID를 받은 경우
고객이 존재하는지
고객 API를 쿼리할 때
고객 ID를 사용한다
그럼 고객 세부사항을 가져온다

시나리오2 : 존재하지 않는 고객 가져오기
고객으로부터 ID를 받은 경우
고객이 존재하지 않고
고객 API를 쿼리할 때
고객 ID를 사용한다
그럼 존재하지 않음 응답을 한다.
```
### CI/CD 처리
CI : Continuous Integration, 지속적인 통합
CD : Continuous Delivery 지속적인 배포 

#### 지속적인 통합
여러 개발자가 동일한 코드 기반에서 개발하는 경우 지속적 통합을 사용하면 소프트웨어 작업 시 문제를 최소화할 수 있다.
CI의 핵심 아이디어는 저장소에 코드를 푸시할 때마다 테스트가 실행되므로 수정된 코드로 인해 애플리케이션이 손상됐는지 여부를 알 수 있다.

- 모든 것이 준비될 때까지 소프트웨어를 커밋해서는 안된다고 생각할 수도 있는데, 이것은 나머지 코드와 통합할 수 없다는 것을 알기 위해 코드베이스에 여러 변경사ㅏ항을 한꺼번에 푸시하는 고통스러운 접근 방법임이 판명됐다.
- 작은 것을 자주 커밋하려고 노력하자. 이것은 통합을 하기 전까지 적은 노력으로 문제를 해결할 수 있게 한다.

#### 지속적인 배포
- 최종 사용자가 사용하지 않는 것은 실제로 아무것도 아닌 것을 의미
- 자주 배포하는 것이 좋으며, 이것은 애플리케이션에 가치를 부여하는 방법이며, 최상의 품질을 위해서는 CD를 해야한다.
