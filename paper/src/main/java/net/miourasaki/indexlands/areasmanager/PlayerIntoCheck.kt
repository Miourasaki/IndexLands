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
package net.miourasaki.indexlands.areasmanager

import net.pl3x.map.core.event.EventHandler
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.entity.Player
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.scheduler.BukkitRunnable

class PlayerIntoCheck : BukkitRunnable(), Listener {
    override fun run() {
        for (player in Bukkit.getOnlinePlayers()) {
            check(player)
        }
    }

    private fun check(player: Player) {
        val location = player.location
        val zeroChunk = Location(location.world, 0.0,0.0, location.z).chunk
        if (location.chunk == zeroChunk) {
           player.sendMessage("${player.name} is already installed")
        }
    }

    @EventHandler
    fun onPlayerJoinEvent(event: PlayerJoinEvent) {
        check(event.player)
    }
}
