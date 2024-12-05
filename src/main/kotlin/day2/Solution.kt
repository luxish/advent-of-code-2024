package me.luxish.aoc.day2

import java.io.File
import java.util.stream.Stream

fun readInputFile(): Stream<String> {
    return File("src/main/kotlin/day2/input.txt").readLines().stream()
}

fun dif(inc: Boolean, item1: Int, item2:Int) : Int {
    return if (inc) item1 - item2 else item2 - item1
}

fun part1(): Int {
    var safe = 0
    readInputFile().forEach { line ->
        val levels = line.split(" ").map { it.toInt() }
        if (levels.size >= 2) {
            val inc = (levels[0] - levels[1]) > 0
            for (idx in 0..<levels.size-1) {
                if (dif(inc, levels[idx], levels[idx +1]) !in 1..3) return@forEach
            }
        }
        safe++
    }
    return safe
}

fun part2(): Int {
    var safe = 0
    readInputFile().forEach { line ->
        val levels = line.split(" ").map { it.toInt() }
        if (levels.size >= 2) {
            var skipIdx = -1
            while (!evaluateList(levels, skipIdx) && skipIdx < levels.size) {
                skipIdx++
            }
            if(skipIdx == levels.size) {
                return@forEach
            }
        }
        safe++
    }

    return safe
}

private fun evaluateList(levels: List<Int>, skipIdx: Int): Boolean {
    val newLevels = if(skipIdx != -1 && skipIdx < levels.size) levels - levels[skipIdx] else levels
    val inc = (newLevels[0] - newLevels[1]) > 0
    for (idx in 0..<newLevels.size-1) {
        if (dif(inc, newLevels[idx], newLevels[idx +1]) !in 1..3) return false
    }
    return true
}


fun main() {
    println("Part 1 ${part1()}")
    println("Part 2 ${part2()}")
}
