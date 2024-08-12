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
package net.miourasaki.indexlands.command

import net.miourasaki.indexlands.IndexLands
import net.miourasaki.indexlands.IndexLandsImpl
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandSender

class NeofetchCommand : CommandATabExecutor() {
    override fun onCommand(p0: CommandSender, p1: Command, p2: String, p3: Array<out String>?): Boolean {
        run(p0)
        return true

    }

    companion object {
    fun run(p0: CommandSender) {
        val playerName = p0.name
        val serverName = Bukkit.getServer().name

        val pluginVersion = IndexLandsImpl.instance.description.version
        val bukkitVersion = Bukkit.getBukkitVersion()

        val osName = System.getProperty("os.name")
        val javaVersion = System.getProperty("java.version")
        println("Operating System: $osName")
        println("Java Version: $javaVersion")


        val p: String
        val i: String
        val s: String



        if (true) {
            p = "green:red>⭐ "
            i = "#5e4fa2:#f79459"
            s = ""
        }else {
            p = "gray:gray>🏗 "
            i = "gray:gray"
            s ="<red>You are using the free build version\n" +
                    "<u><click:open_url:'https://mio.am'>click here buy to support"
        }





        p0.sendMessage(
            IndexLands.getTranslate().parseString("\n" +
                    "<gradient:$i> ⣿⣿⣿   ⣿⣿⣿   ⣿⣿⣿</gradient>  <gray>========================</gray>\n" +
                    "<gradient:$i> ⣿⣿⣿   ⣿⣿⣿   ⣿⣿⣿</gradient>  <gradient:$p"+"IndexLands-$serverName-Alpha\n</gradient>" +
                    "<gradient:$i>  ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿</gradient>   <yellow>$playerName</yellow>@<yellow>$serverName</yellow>\n" +
                    "<gradient:$i>  ⣿⣿⣿ ⣿⣿⣿ ⣿⣿⣿⣿⣿</gradient>   <gray>------------------------</gray>\n" +
                    "<gradient:$i>   ⣿⣿⣿ ⣿⣿ ⣿⣿⣿⣿</gradient>    <yellow>version:</yellow> $pluginVersion\n" +
                    "<gradient:$i>   ⣿⣿⣿⣿ ⣿ ⣿⣿⣿⣿</gradient>    <yellow>os:</yellow> $osName - jdk$javaVersion\n" +
                    "<gradient:$i>  ⣿⣿⣿⣿⣿⣿ ⣿⣿⣿⣿⣿⣿</gradient>   <yellow>core:</yellow> $bukkitVersion\n" +
                    "<gradient:$i> ⣿⣿⣿⣿⣿⣿⣿⣿⣿ ⣿⣿⣿⣿⣿⣿</gradient> <gray>========================</gray>\n" + s
                , null)
        )

    }
    }
}
