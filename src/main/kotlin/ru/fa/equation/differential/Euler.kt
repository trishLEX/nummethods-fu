package ru.fa.equation.differential

import ru.fa.model.Method
import ru.fa.model.MethodResult
import ru.fa.model.Value

class Euler(
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
            y += derivative(point) * h
            point = Point(x, y)
            res.add(point)
        }
        return MethodResult(res, n)
    }
}