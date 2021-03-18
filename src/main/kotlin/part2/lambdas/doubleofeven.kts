// 명령형 프로그래밍 방식
var doubleOfEven = mutableListOf<Int>()
for (i in 1..10) {
    if (i % 2 == 0) {
        doubleOfEven.add(i * 2)
    }
}

// 함수형 프로그래밍 방식
val doubleOfEven1 = (1..10)
    .filter { e -> e % 2 == 0 }
    .map { e -> e * 2 }
