# Chapter 05

## Smart Cast

- Java와 Kotlin의 캐스트 비교
- equals, smartcast, unsafecast

## Kotlin Null에 대한 자세

- nonull

## Generic

### where

- 제네릭을 사용할 때 조건적인 제너릭 클래스만 받을 수 있다.
- closeerr

### Reified

- 제너릭을 사용하면서 제너릭을 구체화시켜 편한 코드를 작성할 수 있다.
- inline도 살짝 맛보기
- reified

### 공변성과 반공변성

- typeinvariance

### 스타 프로젝션

- star

## 정리

- nullable 레퍼런스 타입을 non-nullable 레퍼런스 타입에서 분리하면서 컴파일러는 메모리 오버헤드 없이 안정적인 타입 안정성을 지닌다.
- nullable 레퍼런스로부터 쉽고 유연하게 객체를 가지고 올 수 있는 몇가지 연산자도 제공한다.
- 스마트 캐스트 기능은 불필요한 캐스팅을 할 필요 없게 하면서 코드의 복잡성을 줄여준다.
- 제네릭 함수와 클래스를 사용할 때파라미터 타입을 조정하여 타입 안정성과 유연성을 제공.
- reified 타입 파라미터는 컴파일 시간 타입 안정성을 강화해서 코드의 클러터와 오류를 제거.