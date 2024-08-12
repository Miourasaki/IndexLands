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

package io.dev_m.indexlands

import org.bukkit.Chunk
import org.bukkit.Location
import org.bukkit.World

class GetCheckPoint {

    companion object {
        /**
         * 获取给定位置的区块坐标
         *
         * @param location 要查询的位置
         * @return 包含区块X和Z坐标的Chunk对象
         */
        fun getChunkAtLocation(location: Location?): Chunk? {
            // 确保位置不为空
            if (location == null) {
                return null
            }


            // 获取世界对象
            val world: World = location.world


            // 计算区块的X和Z坐标
            val chunkX = location.blockX shr 4 // 等同于 location.getBlockX() / 16
            val chunkZ = location.blockZ shr 4 // 等同于 location.getBlockZ() / 16


            // 使用世界和区块坐标获取区块对象
            return world.getChunkAt(chunkX, chunkZ)
        }
    }

}