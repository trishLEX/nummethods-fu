package ru.fa.systems

data class FunctionVector(val v: Array<Function>) {

    constructor(n: Int): this(Array(n) { Function { 0.0 } })

    operator fun invoke(vec: Vector): Vector {
        return Vector(v.map { it(vec) }.toTypedArray())
    }

    operator fun get(i: Int): Function {
        return v[i]
    }

    fun size(): Int {
        return v.size
    }

    fun changeElements(i: Int, j: Int): FunctionVector {
        val res = copy()
        val temp = res[i]
        res[i] = res[j]
        res[j] = temp
        return res
    }

    operator fun div(t: Double): FunctionVector {
        val new = v.map { it / t }.toTypedArray()
        return FunctionVector(new)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is FunctionVector) return false

        if (!v.contentEquals(other.v)) return false

        return true
    }

    override fun hashCode(): Int {
        return v.contentHashCode()
    }

    operator fun set(i: Int, value: Function) {
        v[i] = value
    }

    operator fun times(d: Double): FunctionVector {
        return FunctionVector(v.map { it * d }.toTypedArray())
    }

    operator fun minus(vec: FunctionVector): FunctionVector {
        val res = FunctionVector(vec.size())
        for (i in 0 until size()) {
            res[i] = this[i] - vec[i]
        }
        return res
    }

    fun max(vec: Vector): Double {
        return v
            .map { it(vec) }
            .max()
    }
}