package ru.fa

import ru.fa.integration.Integration


private val function: (Value) -> Value = {x -> 7.0 / (x * x + 1.0)}

private val difFunction: (Value) -> Value = {x -> -14.0 * x / (x * x + 1.0).pow(2)}

private const val e = 0.01
private val start = Value(0.0, e)
private val end = Value(5.0, e)
private const val n = 10



fun main(args: Array<String>) {
    val integration = Integration(function, start, end)

    println(integration.trapeze(n, difFunction))
}