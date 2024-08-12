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
package net.miourasaki.indexlands.common.database.nosql

import com.mongodb.client.MongoClients
import com.mongodb.client.MongoDatabase
import net.miourasaki.indexlands.common.database.DataStorageInterface
import org.bukkit.configuration.file.YamlConfiguration

class MongoDBImpl : DataStorageInterface {
    
    private lateinit var mongoDatabase: MongoDatabase
    
    override fun initialize(config: YamlConfiguration): Boolean {
        val mongoHost = config.getString("MongoDB.host")
        val mongoPort = config.getString("MongoDB.port")
        val mongoUser = config.getString("MongoDB.user")
        val mongoPassword = config.getString("MongoDB.password")
        val mongoConnectUri = config.getString("MongoDB.connection-uri")
        val mongoDatabase = config.getString("MongoDB.database")
        val connuri: String = if (mongoConnectUri != null && mongoConnectUri != "") {
            mongoConnectUri
        }else {
            if (mongoHost != null && mongoPort != null) {
                if (mongoUser != null) "mongodb://$mongoUser:$mongoPassword@$mongoHost:$mongoPort/"
                else "mongodb://$mongoHost:$mongoPort/"
            } else return false
        }

        try {
            val mongoClient = MongoClients.create(connuri)
            this.mongoDatabase = mongoClient.getDatabase(mongoDatabase!!)
        }catch(e:Exception) {
            return false
        }
        return true
    }

    override fun getMongoConn(): MongoDatabase {
        return mongoDatabase
    }
}