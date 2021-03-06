# Chapter 16

## 비동기 프로그래밍

- 서비스 호출, DB업데이트, 검색 등 해야하는 일이 발생한다. 이런 대부분의 동작은 즉시 수행되지 않는다.
- 프로그램의 효율성을 올리기 위해서, 위와 같은 동장들은 비동기로 실행하여 비차단방식을 사용한다.
- 코루틴은 정확히 이런 문제를 해결하기 위한 목적으로 탄생!

## 외부 연동 동기 프로그램

- Airport, AirportInfo

## 비동기 프로그램

- AirportInfoAsync

## 예외처리

- DB 업데이트, 파일 엑세스, 외부 API 통신 중 예외가 발생할 수 있습니다 .
- 델리게이팅 할 때 우리는 방어적이어야 하고 실패처리를 해야한다.

```
구조화된 동시성을 주의하자!

스코프가 명시적으로 지정되어 있지 않다면, 코루틴은 컨텍스트와 부모 코루틴의 스코프에서 실행.
이를 구조화된 동시실행이라고 한다. 구조화된 동시 실행이란 코루틴의 계층구조가 코드의 구조와 일치할 때 일어난다.
구조화된 동시실행은 우리가 시작한 코루틴의 실행을 관리하고 모니터하는 것을 쉽게 만들고 코루틴은 자식 코루틴이 모두 완료될 때까지 완료되지 않는다.
이는 코루틴이 어떻게 서로 협력하고 실패했을 어떻게 다뤄지는지에 따라 바뀐다.

코루틴이 예외와 함께 실패했을 때 부모 코루틴도 함께 실패하는 게 기본 동작이다!
SupervisorJob 컨텍스트를 이용해서 자식 코루틴이 부모 코루틴을 취소하는 것을 방지하는 방법을 알아본다.
우리는 SupervisorJob을 이용해서 자식 코루틴에서 예외가 발생했을 때 부모 코루틴을 취소하지 못하도록 막는 방법을 알아보자!
```

- LaunchErr, LaunchErrHandle
- AsyncErr

### 취소와 타임아웃

- 코루틴은 취소될 수 있다.
- Java의 스레드 종료와는 연관성이 없다
- launch()가 리턴하는 Job 객체와 async()가 리턴하는 Deferred<T> 객체에는 각각 cancel() 메소드와 cancelAndJoin() 메소드가 있다.
- 주의사항! 코루틴은 현재 서스펜션 포인트(중단점)에 있는 경우에만 취소가 가능하다. 코루틴이 동작중이라면 취소 알림을 받지 못하고 빠져나오지 못한다.

- 코틀린은 컨텍스트를 공유하는 다수의 코루틴이 계층관계를 구성할때 구조적 동시성을 제공한다.
  ```
  - 코루틴에서 컨텍스트를 공유하는 새로운 코루틴을 생성하면 새 코루틴은 기존 코루틴의 자식으로 간주!
  - 부모 코루틴은 자식 코루틴이 완료되어야만 완료될 수 있다.
  - 부모 코루틴을 취소하면 모든 자식 코루틴이 취소
  - 서스펜션 포인트에 진입한 코루틴은 서스펜션 포인트에서 던져진 CancellationException을 받을 수 있다.
  - 실행되고 있는 코루틴이 서스펜션 포인트에 진입하지 않는 경우 isActive 프로퍼티를 체크해 동작 중에 취소되었는지 여부 확인 가능
  - 정리해야 할 자원을 가진 코루틴은 finally 블록에서 정리해야함
  - 처리되지 않은 예외는 코루틴을 취소시킴
  - 자식 코루틴이 정지하면 부모 코루틴이 정지하므로 형제 코루틴도 취소되고 만다. 이런 동작은 부모에서 자식으로만 단반향으로 취소가 가능하게 만드는 슈퍼바이저 잡을 통해서 변경할 수 있다.
  ```

### 코루틴 취소

- cancelandsupension

### 방해금지

- donotdisturb

### 양방향 취소

- cancellationbidirectional

### 슈퍼바이저 잡

- cancellationbidirectionalsupervisorjob

### 타임아웃을 이용한 프로그래밍

- cancellationbidirectionaltimeout

## 정리

- 코루틴은 비동기적으로 실행할 때뿐만 아니라 현명하고 쉽게 예외를 처리하는데도 좋은 방법을 제공한다.
- 코루틴은 동시실행, 비동기를 동기, 순차적 코드와 유사한 구조로 유지시킴
- 유지보수, 디버그하기 쉽게 도와줌
- 작업의 복잡한 관계를 코루틴의 계층구조로 맵핑하는 것을 실행의 생명주기를 관리하기 쉽게 만들어준다.
- 타임아웃을 사용하면 실행시간 제어 가능
- 슈퍼바이저 잡을 설정하면 계층구조 내의 코루틴의 상호작용 제어 가능!