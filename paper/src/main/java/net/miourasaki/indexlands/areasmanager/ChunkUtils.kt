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

import org.bukkit.Chunk
import org.bukkit.Location
import org.bukkit.World

object ChunkUtils {

    fun get(location: Location): Chunk {



        return location.world.getChunkAt(location)
    }
    fun get(world: World, x:Int, z:Int): Chunk {

        val chunkX = x shr 4 // 等同于 location.getBlockX() / 16
        val chunkZ = z shr 4 // 等同于 location.getBlockZ() / 16
        return world.getChunkAt(chunkX,chunkZ)
    }
}