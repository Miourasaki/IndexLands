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

class MasterConfig(
    private val instance: IndexLandsImpl
) {

    init {
        DatabaseConfig(instance).also { this.databaseConfig = it }
    }

    private val file: File = File(instance.dataFolder ,PATH)
    private lateinit var config: YamlConfiguration
    private var databaseConfig: DatabaseConfig

    fun initialize(): Boolean {
        if (!file.exists()) {
            this.instance.saveResource(PATH, false)
            this.instance.saveResource("LICENSE", false)
        }
        config = YamlConfiguration.loadConfiguration(file)

        this.config.set("config-version", "1")
        this.config.save(file)


        this.enable = config.getBoolean("enable")
        if (enable) enable = databaseConfig.initialize()
        if (!enable) IndexLandsImpl.instance.logger.warning("IndexLands is initialize failed! Modify the ->\"enable\"<- option in \"plugins/IndexLands/config.yml\", Or check the database configuration")
        this.debug = config.getBoolean("debug")
        if (debug) IndexLandsImpl.instance.logger.warning("Debug Mode is enabled! Do not enable in production environments !!!")

        this.lang = Option("lang", config, file)

        return true
    }

    var enable: Boolean = false
    var debug: Boolean = false

    lateinit var lang: Option<String>


    companion object {
        private const val PATH = "config.yml"
    }
}

