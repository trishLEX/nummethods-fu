package ru.fa.systems

data class Matrix(val m: Array<Vector>) {

    operator fun get(i: Int): Vector {
        return m[i]
    }

    fun size(): Int {
        return m[0].size()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Matrix) return false

        if (!m.contentEquals(other.m)) return false

        return true
    }

    override fun hashCode(): Int {
        return m.contentHashCode()
    }

    operator fun set(i: Int, value: Vector) {
        m[i] = value
    }
}