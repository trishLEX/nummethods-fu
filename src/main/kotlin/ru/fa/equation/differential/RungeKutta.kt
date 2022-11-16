package ru.fa.equation.differential

import ru.fa.Method
import ru.fa.MethodResult
import ru.fa.Value

class RungeKutta(
    private val derivative: (Point) -> Value,
    private val start: Point,
    private val h: Double,
    private val n: Int
) : Method<List<Point>> {

    override fun evaluate(): MethodResult<List<Point>> {
        val res = ArrayList<Point>(n)
        res.add(start)
        var x = start.x
        var y = start.y
        var point = Point(x, y)
        for (i in 2 .. n) {
            x += h
            y += phi(point, h) * h
            point = Point(x, y)
            res.add(point)
        }
        return MethodResult(res, n)
    }

    private fun phi(p: Point, h: Double): Value {
        val point = Point(p.x + h, p.y + derivative(p) * h)
        return (derivative(p) + derivative(point)) * 0.5
    }
}