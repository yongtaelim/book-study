/*
재귀의 강점

분할 정복기법...
문제를 해결할 때 문제를 작게 쪼개서 각 부분의 솔류션을 구현한 후 각 결과를 합쳐서 해결하는 기법
 */

fun sort(numbers: List<Int>): List<Int> =
    if (numbers.isEmpty()) {
        numbers
    } else {
        val pivot = numbers.first()
        val tail = numbers.drop(1)
        val lessOrEqual = tail.filter { e -> e <= pivot }
        val larger = tail.filter { e -> e > pivot }
        sort(lessOrEqual) + pivot + sort(larger)
    }

println(sort(listOf(12, 5, 15, 12, 8, 19)))

