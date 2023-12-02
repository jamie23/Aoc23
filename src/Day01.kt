fun main() {
    fun part1(input: List<String>): Int {
        var total = 0

        input.forEach {
            total += it.first { i -> i.isDigit() }.digitToInt() * 10
            total += it.last { i -> i.isDigit() }.digitToInt()
        }

        return total
    }

    fun part2(input: List<String>): Int {
        var total = 0

        val mapNumToString = mapOf(
            "zero" to 0,
            "one" to 1,
            "two" to 2,
            "three" to 3,
            "four" to 4,
            "five" to 5,
            "six" to 6,
            "seven" to 7,
            "eight" to 8,
            "nine" to 9,
        )

        input.forEach { line ->
            val firstInt = line.indexOfFirstOrMaxInt()
            val secondInt = line.indexOfLastOrMinInt()

            val firstWord = line.findAnyOf(mapNumToString.keys) ?: Pair(Int.MAX_VALUE, "nine")
            val lastWord = line.findLastAnyOf(mapNumToString.keys) ?: Pair(Int.MIN_VALUE, "nine")

            val firstWordPairInt: Pair<Int, Int> = Pair(firstWord.first, mapNumToString[firstWord.second] ?: -1)
            val secondWordPairInt: Pair<Int, Int> = Pair(lastWord.first, mapNumToString[lastWord.second] ?: -1)

            total += if (firstInt.first < firstWordPairInt.first) (firstInt.second * 10) else (firstWordPairInt.second * 10)
            total += if (secondInt.first > secondWordPairInt.first) secondInt.second else secondWordPairInt.second
        }

        return total
    }

    // test if implementation meets criteria from the description, like:
    val input = readInput("Day01")

    val testInput = readInput("Day01_test")
    check(part1(testInput) == 142)
    part1(input).println()

    val testInputB = readInput("Day01b_test")
    check(part2(testInputB) == 281)
    part2(input).println()
}

fun String.indexOfFirstOrMaxInt(): Pair<Int, Int> {
    val index = this.indexOfFirst { it.isDigit() }
    if (index == -1) return Pair(Int.MAX_VALUE, Int.MAX_VALUE)
    return Pair(index, this[index].digitToInt())
}

fun String.indexOfLastOrMinInt(): Pair<Int, Int> {
    val index = this.indexOfLast { it.isDigit() }
    if (index == -1) return Pair(Int.MIN_VALUE, Int.MIN_VALUE)
    return Pair(index, this[index].digitToInt())
}