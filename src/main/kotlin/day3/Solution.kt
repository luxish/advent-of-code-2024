package me.luxish.aoc.day3

import java.io.File
import java.util.stream.Stream

fun readInputFile(): Stream<String> {
    return File("src/main/kotlin/day3/input.txt").readLines().stream()
}

fun part1(): Int {
    var sum = 0
    readInputFile().forEach { line: String ->
        println("Line $line")
        val matches = """mul\((?<n1>\d+),(?<n2>\d+)\)""".toRegex().findAll(line)
        matches.forEach {
            sum += (it.groups["n1"]?.value?.toInt() ?: 0) * (it.groups["n2"]?.value?.toInt() ?: 0)
        }
    }
    return sum
}

fun part2(): Int {
    // later
    return 0
}

fun main() {
    println("Part 1 ${part1()}")
    println("Part 2 ${part2()}")
}
