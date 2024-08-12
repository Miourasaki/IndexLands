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
import net.miourasaki.indexlands.common.database.DBManager
import org.bukkit.configuration.file.YamlConfiguration
import java.io.File

class DatabaseConfig(
    private val instance: IndexLandsImpl
) {

    private val file: File = File(instance.dataFolder ,PATH)
    private lateinit var config: YamlConfiguration

    fun initialize(): Boolean {
        if (!file.exists()) {
            this.instance.saveResource(PATH, false)
        }
        config = YamlConfiguration.loadConfiguration(file)

        this.config.set("database-type", "MongoDB")
        this.config.save(file)

        return DBManager.initDBConnection(config)
    }

    companion object {
        private const val PATH = "database.yml"
    }
}

