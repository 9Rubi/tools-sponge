package ink.rubi.tools

import com.google.inject.Inject
import org.slf4j.Logger
import org.spongepowered.api.Sponge
import org.spongepowered.api.command.CommandResult
import org.spongepowered.api.command.args.GenericArguments
import org.spongepowered.api.command.spec.CommandSpec
import org.spongepowered.api.entity.living.player.Player
import org.spongepowered.api.event.Listener
import org.spongepowered.api.event.game.state.GameInitializationEvent
import org.spongepowered.api.plugin.Plugin
import org.spongepowered.api.plugin.PluginContainer
import org.spongepowered.api.text.Text
import java.util.concurrent.ConcurrentHashMap
import kotlin.math.cos
import kotlin.math.sin

@Plugin(
    id = "rubi-tools-plugin", name = "rubi tools plugin", version = "0.0.1", description = """
    一些工具集合.
"""
)
class ToolsPlugin {
    @Inject
    private lateinit var logger: Logger
    @Inject
    private lateinit var plugin: PluginContainer

    private val points = ConcurrentHashMap<String, MutableList<Point>>()

    @Listener
    fun initPlugin(event: GameInitializationEvent) {
        val commandManager = Sponge.getCommandManager()
        with(commandManager) {
            register(
                plugin,
                CommandSpec.builder().executor { src, _ ->
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
                }.build(), "recordeye"
            )
            register(
                plugin,
                CommandSpec.builder().executor { src, _ ->
                    (src as? Player)?.let {
                        points[it.name]?.forEachIndexed { index, point ->
                            it.sendMessage(Text.of("[index: $index ], x: ${point.x} , z: ${point.x} ,facing: ${point.facing}"))
                        }
                    }
                    CommandResult.success()
                }.build(), "recordeyelist"
            )
            register(
                plugin,
                CommandSpec.builder().executor { src, _ ->
                    (src as? Player)?.let {
                        points[it.name]?.clear()
                        it.sendMessage(Text.of("clear record points"))
                    }
                    CommandResult.success()
                }.build(), "recordeyeclear"
            )
            register(
                plugin,
                CommandSpec.builder().arguments(
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
                }.build(), "recordeyeguess"
            )
        }
        logger.info("rubi's tools kit loaded!")
    }
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