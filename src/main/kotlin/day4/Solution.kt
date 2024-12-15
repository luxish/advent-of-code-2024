package me.luxish.aoc.day4

import java.io.File
import java.util.stream.Stream

fun readInputFile(): Stream<String> {
    return File("src/main/kotlin/day4/input.txt").readLines().stream()
}

enum class Direction(val stepRow: Int, val stepCol: Int) {
    LEFT(0, -1),
    RIGHT(0, 1),
    UP(-1, 0),
    DOWN(1, 0),
    DIAG_UP_RIGHT(-1, 1),
    DIAG_UP_LEFT(-1, -1),
    DIAG_DOWN_RIGHT(1, 1),
    DIAG_DOWN_LEFT(1, -1)
}

fun wordOn(matrix: MutableList<List<String>>, direction: Direction, irow: Int, icol: Int, wordLength: Int): String {
    var word = ""
    for (idx in 0..<wordLength) {
        word += matrix[irow + direction.stepRow * idx][icol + direction.stepCol * idx]
    }
    return word
}

fun part1(): Int {
    var sum = 0
    val word = "XMAS"
    val matrix = mutableListOf<List<String>>()
    readInputFile().forEach { line: String ->
        val lineList = mutableListOf<String>()
        line.split("", line).filter { it != "" }.toCollection(lineList)
        matrix.add(lineList)
    }

    for (irow in matrix.indices) {
        for (icol in matrix[irow].indices) {
            if (icol - word.length + 1 >= 0 && word == wordOn(matrix, Direction.LEFT, irow, icol, word.length)) {
                sum++
            }
            if (icol + word.length <= matrix[irow].size
                && word == wordOn(matrix, Direction.RIGHT, irow, icol, word.length)
            ) {
                sum++
            }
            if (irow - word.length + 1 >= 0 && word == wordOn(matrix, Direction.UP, irow, icol, word.length)) {
                sum++
            }
            if (irow + word.length <= matrix.size && word == wordOn(matrix, Direction.DOWN, irow, icol, word.length)) {
                sum++
            }
            if (irow - word.length + 1 >= 0 && icol - word.length + 1 >= 0
                && word == wordOn(matrix, Direction.DIAG_UP_LEFT, irow, icol, word.length)
            ) {
                sum++
            }
            if (irow - word.length + 1 >= 0 && icol + word.length <= matrix[irow].size
                && word == wordOn(matrix, Direction.DIAG_UP_RIGHT, irow, icol, word.length)
            ) {
                sum++
            }
            if (irow + word.length <= matrix.size && icol - word.length + 1 >= 0
                && word == wordOn(matrix, Direction.DIAG_DOWN_LEFT, irow, icol, word.length)
            ) {
                sum++
            }
            if (irow + word.length <= matrix.size && icol + word.length <= matrix[irow].size
                && word == wordOn(matrix, Direction.DIAG_DOWN_RIGHT, irow, icol, word.length)
            ) {
                sum++
            }
        }
    }

    return sum
}

fun part2(): Int {
    var sum = 0
    val word = "MAS"
    val matrix = mutableListOf<List<String>>()
    readInputFile().forEach { line: String ->
        val lineList = mutableListOf<String>()
        line.split("", line).filter { it != "" }.toCollection(lineList)
        matrix.add(lineList)
    }
    for (irow in 1..<matrix.size - 1) {
        for (icol in 1..<matrix[irow].size - 1) {
            val word1 = matrix[irow - 1][icol - 1] + matrix[irow][icol] + matrix[irow + 1][icol + 1]
            val word2 = matrix[irow - 1][icol + 1] + matrix[irow][icol] + matrix[irow + 1][icol - 1]
            if ((word1 == word || word1.reversed() == word) && (word2 == word || word2.reversed() == word)) {
                sum++
            }
        }
    }
    return sum
}

fun main() {
    println("Part 1 ${part1()}")
    println("Part 2 ${part2()}")
}
