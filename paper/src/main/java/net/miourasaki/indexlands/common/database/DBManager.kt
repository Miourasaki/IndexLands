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

package net.miourasaki.indexlands.common.database

import net.miourasaki.indexlands.common.database.nosql.MongoDBImpl
import org.bukkit.configuration.file.YamlConfiguration

object DBManager {

    lateinit var storage: DataStorageInterface
    var prefix: String = ""

    fun initDBConnection(config: YamlConfiguration): Boolean {
        val prefix = config.getString("table-prefix")
        if (prefix != null) this.prefix = prefix + "_"


        val dbType = config.getString("database-type")

        when (dbType?.uppercase()) {
            "MONGODB" -> {
                this.storage = MongoDBImpl()
                return storage.initialize(config)
            }
        }

        return false;
    }


}