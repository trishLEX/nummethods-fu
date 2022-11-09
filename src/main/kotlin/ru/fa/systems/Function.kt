package ru.fa.systems

class Function(
    val f: (Vector) -> Double
) {
    operator fun times(d: Double): Function {
        return Function{ f(it) * d}
    }

    operator fun invoke(vec: Vector): Double {
        return f(vec)
    }

    operator fun div(t: Double): Function {
        return Function { f(it) / t }
    }

    operator fun minus(function: Function): Function {
        return Function { f(it) - function(it) }
    }

    operator fun times(function: Function): Function {
        return Function { f(it) * function(it) }
    }
}