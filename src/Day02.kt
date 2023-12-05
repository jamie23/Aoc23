import Color.*
import java.lang.Exception

fun main() {
    fun part1(input: List<String>): Int {
        // if the bag had been loaded with only 12 red cubes, 13 green cubes, and 14 blue cubes

        var total = 0

        input.forEachIndexed { index, line ->
            val cubes = line
                .substringAfter(": ")
                .split(",", ";")
                .map { it.trim() }
                .map {
                    Cube.creator(it.substringBefore(" ").toInt(), it.substringAfter(" ")[0])
                }

            if (cubes.all { it.isValid }) {
                total += index + 1
            }
        }

        return total
    }

    fun part2(input: List<String>): Int {
        var total = 0

        input.forEach { line ->
            val cubes = line
                .substringAfter(": ")
                .split(",", ";")
                .map { it.trim() }
                .map {
                    Cube.creator(it.substringBefore(" ").toInt(), it.substringAfter(" ")[0])
                }

            total += cubes.filter { it.type == RED }.maxOf { it.amount } *
                    cubes.filter { it.type == GREEN }.maxOf { it.amount } *
                    cubes.filter { it.type == BLUE }.maxOf { it.amount }
        }

        return total
    }

    // test if implementation meets criteria from the description, like:
    val input = readInput("Day02")

    val testInput = readInput("Day02_test")
    check(part1(testInput) == 8)
    part1(input).println()

    val testInputB = readInput("Day02_test")
    check(part2(testInputB) == 2286)
    part2(input).println()
}

data class Cube(val amount: Int, val type: Color) {
    val isValid: Boolean = amount <= type.max

    companion object {
        fun creator(amount: Int, c: Char) = when (c) {
            'r' -> Cube(amount, RED)
            'g' -> Cube(amount, GREEN)
            'b' -> Cube(amount, BLUE)
            else -> throw Exception("Unsupported color")
        }
    }
}

enum class Color(val max: Int) {
    RED(12),
    GREEN(13),
    BLUE(14)
}