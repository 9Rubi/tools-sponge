package ink.rubi.tools


/*

class Line(p1: Point, p2: Point) {
    val k: Double = (p2.z - p1.z) / (p2.x - p1.x)
    val b: Double = ((p1.z - (p1.x * k)) + (p2.z - (p2.x * k))) / 2
    fun willTouchAt(otherLine: Line): Point {
        val x = (otherLine.b - this.b) / (this.k - otherLine.k)
        val z = ((x * k + b) + (x * otherLine.k + otherLine.b)) / 2
        return Point(x,z)
    }
}

class ThreeLine(val a:Line,val b:Line,val c:Line){
    fun getFinalPoint(): Point {
        val pointA = a.willTouchAt(b)
        val pointB = b.willTouchAt(c)
        val pointC = a.willTouchAt(c)
        val maxX = pointA.x.coerceAtLeast(pointB.x).coerceAtLeast(pointC.x)
        val maxZ = pointA.z.coerceAtLeast(pointB.z).coerceAtLeast(pointC.z)
        val minX = pointA.x.coerceAtMost(pointB.x).coerceAtMost(pointC.x)
        val minZ = pointA.z.coerceAtMost(pointB.z).coerceAtMost(pointC.z)
        return Point((maxX + minX)/2, (maxZ + minZ)/2)
    }
}

*/
