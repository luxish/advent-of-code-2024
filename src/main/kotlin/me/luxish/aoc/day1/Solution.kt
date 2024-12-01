package me.luxish.aoc.day1

import java.io.File
import java.util.stream.Stream
import kotlin.math.abs

fun readInputFile(): Stream<String> {
    return File("src/main/kotlin/me/luxish/aoc/day1/input.txt").readLines().stream()
}

fun addToListInOrder(list: MutableList<Long>, locationId: Long) {
    if (list.isEmpty() || list[list.size - 1] < locationId) {
        list.add(locationId)
        return
    }
    for (idx in list.indices) {
        if (list[idx] > locationId) {
            var k = list.size - 1
            list.add(list[list.size - 1])
            while (k > idx) {
                list[k] = list[k - 1]
                k--
            }
            list[idx] = locationId
            break
        }
    }
}

fun part1(): Long {
    val l1: MutableList<Long> = mutableListOf()
    val l2: MutableList<Long> = mutableListOf()

    readInputFile().forEach { line ->
        val locIdTokens = line.split(" ").stream()
            .filter(String::isNotEmpty)
            .map { lid: String -> lid.toLong() }
            .toList()
        assert(locIdTokens.size == 2)

        addToListInOrder(l1, locIdTokens[0])
        addToListInOrder(l2, locIdTokens[1])
    }

    var sum: Long = 0
    for (idx in l1.indices) {
        sum += abs(l1[idx] - l2[idx])
    }
    return sum
}

fun part2(): Long {
    val l1: MutableMap<Long, Int> = mutableMapOf()
    val l2: MutableMap<Long, Int> = mutableMapOf()
    readInputFile().forEach { line ->
        val locIdTokens = line.split(" ").stream()
            .filter(String::isNotEmpty)
            .map { lid: String -> lid.toLong() }
            .toList()
        assert(locIdTokens.size == 2)

        val loc1: Long = locIdTokens[0]
        l1[loc1] = (l1[loc1] ?: 0) + 1

        val loc2: Long = locIdTokens[1]
        l2[loc2] = (l2[loc2] ?: 0) + 1
    }

    var sum: Long = 0
    for (entry in l1) {
        val oc = l2[entry.key] ?: 0
        sum += entry.key * entry.value * oc
    }
    return sum
}


fun main() {
    println("Part 1 ${part1()}")
    println("Part 2 ${part2()}")
}
