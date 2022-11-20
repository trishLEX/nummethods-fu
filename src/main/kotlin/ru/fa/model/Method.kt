package ru.fa.model

interface Method<T> {
    fun evaluate(): MethodResult<T>
    fun methodError(): Double {
        return 0.0
    }
}