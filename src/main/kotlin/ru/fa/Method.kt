package ru.fa

interface Method {
    fun evaluate(): MethodResult
    fun methodError(): Double
}