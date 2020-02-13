package ink.rubi.tools

import org.spongepowered.api.command.CommandResult
import org.spongepowered.api.command.spec.CommandSpec
import org.spongepowered.api.entity.living.player.Player
import org.spongepowered.api.service.pagination.PaginationList
import org.spongepowered.api.text.Text
import org.spongepowered.api.text.format.TextColors
import java.util.*


fun team(): CommandSpec {
    return CommandSpec.builder().executor { src, _ ->

        val list = listOf<Text>(
            Text.of(TextColors.RED, "First Line"),
            Text.of(TextColors.RED, "Second Line"),
            Text.of(TextColors.RED, "Third Line"),
            Text.of(TextColors.RED, "... Line"),
            Text.of(TextColors.RED, "... Line"),
            Text.of(TextColors.RED, "... Line"),
            Text.of(TextColors.RED, "... Line"),
            Text.of(TextColors.RED, "... Line"),
            Text.of(TextColors.RED, "... Line"),
            Text.of(TextColors.RED, "... Line"),
            Text.of(TextColors.RED, "... Line"),
            Text.of(TextColors.RED, "... Line"),
            Text.of(TextColors.RED, "... Line"),
            Text.of(TextColors.RED, "... Line"),
            Text.of(TextColors.RED, "... Line")
        )
        PaginationList.builder()
            .title(Text.of(TextColors.RED, "PokeTeams"))
            .padding(Text.of(TextColors.AQUA, "="))
            .linesPerPage(5)
            .contents(list)
            .sendTo(src)
        CommandResult.success()
    }.build()

}