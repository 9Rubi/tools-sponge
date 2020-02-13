/*
package ink.rubi.tools

import ink.rubi.tools.ToolsContext.points
import org.spongepowered.api.command.CommandResult
import org.spongepowered.api.command.args.GenericArguments
import org.spongepowered.api.command.spec.CommandSpec
import org.spongepowered.api.entity.living.player.Player
import org.spongepowered.api.text.Text
import kotlin.math.cos
import kotlin.math.sin


fun record(): CommandSpec {
    return CommandSpec.builder()
        .executor { src, _ ->
        (src as? Player)?.let {
            var w = it.rotation.y
            w = when {
                w > 180.0 -> w - 360.0
                w < -180.0 -> w + 360.0
                else -> it.rotation.y
            }
            val current = Point(it.position.x, it.position.z, w)
            val hasPoint = points[it.name]
            if (hasPoint == null)
                points[it.name] = mutableListOf(current)
            else {
                if (hasPoint.size >= 10) hasPoint.clear()
                hasPoint.add(current)
            }
            it.sendMessage(Text.of("add point => [x: ${current.x} ,z: ${current.z} ,facing: ${current.facing}]"))
        }
        CommandResult.success()
    }.child(recordList(),"list")
        .child(recordGuess(),"guess")
        .build()
}

fun recordList(): CommandSpec {
    return CommandSpec.builder().executor { src, args ->
        (src as? Player)?.let {
            points[it.name]?.forEachIndexed { index, point ->
                it.sendMessage(Text.of("[index: $index ], x: ${point.x} , z: ${point.x} ,facing: ${point.facing}"))
            }
        }
        CommandResult.success()
    }.build()
}

fun recordGuess():CommandSpec{
  return  CommandSpec.builder().arguments(
        GenericArguments.integer(Text.of("point 1 index")),
        GenericArguments.integer(Text.of("point 2 index"))
    ).executor { src, args ->
        (src as? Player)?.let {
            val p1 = args.getOne<Int>(Text.of("point 1 index")).get()
            val p2 = args.getOne<Int>(Text.of("point 2 index")).get()
            require(p1 >= 0)
            require(p2 >= 0)
            val records = points[it.name]
            if (records.isNullOrEmpty() || records.size < p1.coerceAtLeast(p2) + 1) {
                src.sendMessage(Text.of("records not enough!"))
            } else {
                val result = records[p1].guess(records[p2])
                src.sendMessage(Text.of("I guess [x= ${result.x}, z= ${result.z}]"))
            }
        }
        CommandResult.success()
    }.build()
}


data class Point(val x: Double, val z: Double, val facing: Double) {
    fun guess(other: Point): Point {
        val x1 = this.x
        val x2 = other.x
        val z1 = this.z
        val z2 = other.z
        val θ1 = this.facing
        val θ2 = other.facing

        val a = x1 * sin(θ2) * cos(θ1) - x2 * sin(θ1) * cos(θ2) + (z1 - z2) * sin(θ1) * sin(θ2)
        val b = z2 * sin(θ2) * cos(θ1) - z1 * sin(θ1) * cos(θ2) - (x1 - x2) * cos(θ1) * cos(θ2)
        val c = sin(θ2 - θ1)
        return Point(a / c, b / c, 0.0)
    }
}

object RecordPointToCalcStrongHold {
    @JvmStatic
    fun main(args: Array<String>) {
        println(Point(-99.442, 1028.999, 118.2).guess(Point(-132.154, 1112.965, 129.0)))
    }
}*/
