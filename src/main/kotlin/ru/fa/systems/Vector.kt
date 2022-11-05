package ru.fa.systems

data class Vector(val v: Array<Double>) {

    operator fun get(i: Int): Double {
        return v[i]
    }

    fun size(): Int {
        return v.size
    }

    fun changeElements(i: Int, j: Int): Vector {
        val res = copy()
        val temp = res[i]
        res[i] = res[j]
        res[j] = temp
        return res
    }

    operator fun div(t: Double): Vector {
        val new = v.map { it / t }.toTypedArray()
        return Vector(new)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Vector) return false

        if (!v.contentEquals(other.v)) return false

        return true
    }

    override fun hashCode(): Int {
        return v.contentHashCode()
    }

    operator fun set(i: Int, value: Double) {
        v[i] = value
    }
}