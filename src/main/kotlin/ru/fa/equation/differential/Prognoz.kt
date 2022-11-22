package ru.fa.equation.differential

import ru.fa.model.Method
import ru.fa.model.MethodResult
import ru.fa.model.Point
import ru.fa.model.Value
import kotlin.math.abs

class Prognoz(
    private val derivative: (Point) -> Value,
    private val start: Point,
    private val h: Double,
    private val n: Int,
    private val e: Double = 0.001
) : Method<List<Point>> {

    override fun evaluate(): MethodResult<List<Point>> {
        val resStart = RungeKutta(derivative, start, h, n).evaluate().result

        val res = ArrayList<Point>(n)
        res.add(resStart[0])
        res.add(resStart[1])

        for (i in 2 until n) {
            val x = res[i - 1].x + h
            val y = res[i - 2].y + derivative(res[i -1]) * 2 * h
            var point = Point(x, y)
            point = step(res[i - 1], point)
            res.add(point)
        }
        return MethodResult(res, n)
    }

    private fun step(prevPoint: Point, prevStepPoint: Point): Point {
        val x = prevStepPoint.x.value
        var y = prevPoint.y.value + (derivative(prevPoint).value + derivative(prevStepPoint).value) * h / 2.0
        var prev = prevStepPoint
        var cur = Point(x, y)

        while (abs(cur.y.value - prev.y.value) >= e) {
            prev = cur
            y = prevPoint.y.value + (derivative(prevPoint).value + derivative(prevStepPoint).value) * h / 2.0
            cur = Point(prev.x.value, y)
        }
        return prev
    }
}