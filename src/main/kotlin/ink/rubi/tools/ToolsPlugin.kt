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
import org.spongepowered.api.service.economy.EconomyService
import org.spongepowered.api.text.Text
import java.util.concurrent.ConcurrentHashMap
import kotlin.math.cos
import kotlin.math.sin

@Plugin(
    id = "rubi-tools-plugin", name = "rubi tools plugin", authors = ["rubi"], version = "0.0.1", description = """
    一些工具集合.
"""
)
class ToolsPlugin {
    @Inject
    private lateinit var logger: Logger

    @Listener
    fun initPlugin(event: GameInitializationEvent) {
        Sponge.getServiceManager().provide(EconomyService::class.java)

       Sponge.getCommandManager().register(this, CommandSpec.builder()
//           .child(record(),"record")
           .child(team(),"team")
           .build(),"rubitools")
        logger.info("rubi's tools kit loaded!")
    }
}