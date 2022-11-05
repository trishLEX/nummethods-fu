package ru.fa

data class MethodResult<T>(val result: T, val methodError: Double, val iterations: Int) {
    constructor(result: T, iterations: Int) : this(result, 0.0, iterations)
}