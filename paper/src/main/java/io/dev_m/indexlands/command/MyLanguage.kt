/*
 *  Copyright (C) <2024> Miourasaki<mio@chyan.moe>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package io.dev_m.indexlands.command

import io.dev_m.indexlands.GetCheckPoint
import net.kyori.adventure.text.minimessage.MiniMessage
import net.miourasaki.indexlands.IndexLands
import net.miourasaki.indexlands.IndexLandsImpl
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class MyLanguage : CommandExecutor {
    override fun onCommand(commandSender: CommandSender, command: Command, s: String, strings: Array<String>): Boolean {


        if (strings.isNotEmpty()) {
            when (strings[0]) {
                "selflang" -> {
                    if (commandSender is Player) {
                        commandSender.sendMessage(commandSender.locale)
                    }
                }
                "configlang" -> {
                    IndexLandsImpl.mainconfig.lang.getValue()?.let { commandSender.sendMessage(it) }
                }
                "plang" -> {
                    if (strings.size >=2 && commandSender is Player) {
                        commandSender.sendMessage(IndexLands.getTranslate().parseKey(strings[1], commandSender))
                    }
                }
                "cp" -> {
                    if (commandSender is Player) {
                        GetCheckPoint.getChunkAtLocation(commandSender.location)?.let {
                            commandSender.sendMessage(
                            MiniMessage.miniMessage().deserialize(
                                String.format("%s, %s", it.x, it.z)
                            )
                        ) }
                    }
                }
            }
        }


        return true
    }
}
