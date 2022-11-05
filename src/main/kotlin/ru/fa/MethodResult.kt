package ru.fa

data class MethodResult(val result: Value, val methodError: Double, val iterations: Int) {
    constructor(result: Value, iterations: Int) : this(result, 0.0, iterations)
}