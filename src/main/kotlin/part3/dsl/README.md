# Chapter 13

`드디어 왔다!!!!!!!!!!!!`

- 도메인 특화언어 (domain-specific languages).. DSL
- 시스템과 의사소통을 하고 코드를 함께 유지보수하는 프로그래머들과도 커뮤니케이션을 한다.
- DSL을 만들기 위해서는 반드시 실험을 하고, 임기응변을 하며, 다양한 스타일도 적용할 수 있어야한다.

## DSL의 타입과 특징

### 내부 DSL을 위한 코틀린

- 생략 가능한 세미콜론
  ```
  starts.at(14.30);
  ends.by(15.20);
  
  starts.at(14.30)
  ends.by(15.20)
  ```
- infix를 이용한 점과 괄호제거
  ```
  starts.at(14.30)
  ends.by(15.20)
  
  starts at 14.30 )
  ends by 15.20
  ```
- 확장함수를 이용한 도메인 특화
  ```
  2.days(ago)
  
  2 days ago 
  ```
- 람다를 전달할 때 괄호는 필요없다
  ```
  "Realease Planning".meeting({
    starts.at(14.30);
    ends.by(15.20);
  })
  
  "Realease Planning" meeting {
    starts at 14.30
    ends by 15.20
  )
  ```
- DSL 생성을 도와주는 암시적 리시버
  ```
  palceOrder {
    an item "Pencil"
    an item "Eraser"
    complete {
      this with creditcard number "1234-5678-1234-5678"
    }
  }
  ```
- DSL을 돕기위한 추가 특징
  ```
  Any 클래스의 메소드들이 DSL의 자연스러움과 표현력, 기능성을 올려준다.
  also(), apply(), let(), run()
  람다 표현식의 단일 파라미터를 참조할 경우 it을 사용한다. this, it 취향에 맞게 쓰자
  ```

## 확장함수 사용

- DateUtil, useDatedsl

## 리시버와 infix 사용

- meetingdsl, meetingdslevoled

## 정리

- 코틀린의 자연스러움, 표현력, 많은 기능과 힘은 코틀린 내부 DSL을 만들기 좋은 언어이다.
- 코틀린은 정적타입 언어이기 때문에 DSL을 사용할 때도 타입 안정성을 제공한다.
- 자신만의 DSL을 설계하고, 유저에게 자연스럽고 많은 기능을 제공하는 API도 설계할 수 있다.



