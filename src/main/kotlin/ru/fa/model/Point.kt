package ru.fa.model

data class Point(val x: Value, val y: Value): Numeric<Point>(NumericType.POINT) {

    constructor(x: Double, y: Double): this(Value(x), Value(y))

    override fun compareTo(other: Point): Int {
        TODO("Not yet implemented")
    }

    override fun times(d: Double): Point {
        return Point(x * d, y * d)
    }

    override fun times(n: Point): Point {
        TODO("Not yet implemented")
    }

    override fun div(d: Double): Point {
        return Point(x / d, y / d)
    }

    override fun <T : Numeric<T>> Double.div(t: T): T {
        TODO("Not yet implemented")
    }

    override fun div(n: Point): Point {
        TODO("Not yet implemented")
    }

    override fun plus(d: Double): Point {
        return Point(x + d, y + d)
    }

    override fun plus(n: Point): Point {
        return Point(x + n.x, y  + n.y)
    }

    override fun minus(d: Double): Point {
        return Point(x - d, y  - d)
    }

    override fun unaryMinus(): Point {
        return Point(-x, -y)
    }

    override fun abs(): Point {
        return Point(x.abs(), y.abs())
    }

    override fun one(): Point {
        return Point(1.0, 1.0)
    }

    override fun zero(): Point {
        return Point(0.0, 0.0)
    }

    override fun self(): Point {
        return this
    }

    override fun copy(): Point {
        return copy(x = x, y = y)
    }

    override fun minus(n: Point): Point {
        return Point(x - n.x, y - n.y)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Point

        if (x != other.x) return false
        if (y != other.y) return false

        return true
    }

    override fun hashCode(): Int {
        var result = x.hashCode()
        result = 31 * result + y.hashCode()
        return result
    }

    override fun toString(): String {
        return "(${x.value}; ${y.value})"
    }
}