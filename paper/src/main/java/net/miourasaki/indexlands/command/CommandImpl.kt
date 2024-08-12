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
import net.miourasaki.indexlands.common.IndexImpl
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabExecutor
import org.bukkit.entity.Player

class CommandImpl(instance: IndexLandsImpl) : IndexImpl(instance), CommandExecutor,TabExecutor {
    override fun onEnable() {
        instance.getCommand("indexlands")?.setExecutor(this)

        sceMap["menu"] = MenuCommand()
        sceMap["create"] = CreatelandCommand()

        sceMap["neofetch"] = NeofetchCommand()
        sceMap["info"] = NeofetchCommand()
    }

    // Sub Command Executor Register Map
    private val sceMap = HashMap<String, CommandATabExecutor>()

    override fun onCommand(
        cs: CommandSender,
        c: Command,
        os: String,
        args: Array<String>
    ): Boolean {

        if (args.isEmpty()) {
            return sceMap["menu"]?.onCommand(cs,c,os,args)!!
        }else {
            val ce = sceMap[args[0]]
            if (ce == null) {
                var target: Player? = null
                if (cs is Player) target = cs
                cs.sendMessage(IndexLands.getTranslate().parseKey("indexlands.command.unknown", target))
                return true
            }

            return ce.onCommand(cs,c,os,args)
        }
    }

    override fun onTabComplete(
        cs: CommandSender,
        c: Command,
        os: String,
        args: Array<out String>?
    ): MutableList<String>? {

        if (args != null)
            if (args.size == 1) {
                return sceMap.keys.toMutableList()
            }else if (args.size > 1) {
                return sceMap[args[0]]?.onTabComplete(cs,c,os,args)
            }

        return null
    }

}
