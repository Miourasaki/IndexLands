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

package net.miourasaki.indexlands.common

import net.kyori.adventure.text.Component
import org.bukkit.entity.Player

interface Translate {

    fun initialize()

    /**
     * 通过翻译键，和配置文件获取最终的文本组件
     * get translation components through configuration files
     *
     * @param key 输入翻译键 Enter translation key
     * @param player 占位符API和本地语言获取 papi and locate target
     * @return 返回kyori文本组件 Return kyori text component
     */
    fun parseKey(key: String, player: Player?): Component


    /**
     * PlaceholderAPI + MiniMessage serialization utils
     *
     * @param string raw string
     * @return Return kyori text component
     */
    fun parseString(string: String, player: Player?): Component

}