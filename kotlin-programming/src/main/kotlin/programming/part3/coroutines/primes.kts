fun primes(start: Int): Sequence<Int> = sequence {
    println("Starting to look")
    var index = start
    while (true) {
        if (index > 1 && (2 until index).none { i -> index % i == 0 }) {
            yield(index)
            println("Generating next after $index")
        }
        index++
    }
}

for (prime in primes(start = 17)) {
    println("Received $prime")
    if (prime > 30) break
}

/*
Starting to look
Received 17
Generating next after 17
Received 19
Generating next after 19
Received 23
Generating next after 23
Received 29
Generating next after 29
Received 31
 */

/*
sequence() 함수는 3개의 장점을 제공!
1. 콜렉션을 미리 만들 필요가 없다. 연산에 얼마나 많은 값을 사용하게 될지 알 필요가 없고 필요할 때마다 값을 생성한다.
2. 시간이 흐를수록 값을 생성하느라 들어간 시간을 아낄 수 있고 이미 생성된 값을 사용하면 된다.
3. 시리즈 값의 생성이 필요할 때만 생성되기 때문에(lazy) 우리는 사용도 안될 값을 생성하는 상황을 피할 수 있다.
 */