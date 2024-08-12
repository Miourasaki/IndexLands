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
package net.miourasaki.indexlands.common.configuration

import net.miourasaki.indexlands.IndexLandsImpl
import org.bukkit.configuration.file.YamlConfiguration
import java.io.File

class Configuration : YamlConfiguration() {
    override fun get(path: String): Any? {
        return super.get(path)
    }
}


class Option<T : Any>(
    private val key: String,
    private var config: YamlConfiguration,
    private val file: File
) {
    private lateinit var value: T

    @Suppress("UNCHECKED_CAST")
    fun getValue(): T? {
        if (IndexLandsImpl.mainconfig.debug) {
            config = YamlConfiguration.loadConfiguration(file)
        }
        val v = config.get(key) ?: return null
        return v as T
    }
    fun setValue(newValue: T) {
        this.config.set(key, newValue)
        this.config.save(file)
        value = newValue
    }
}