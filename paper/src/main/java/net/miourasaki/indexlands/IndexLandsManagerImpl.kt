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
package net.miourasaki.indexlands

import net.miourasaki.indexlands.area.Area
import net.miourasaki.indexlands.common.Translate
import net.miourasaki.indexlands.common.database.DBManager
import net.miourasaki.indexlands.land.Land
import org.bukkit.plugin.java.JavaPlugin
import java.util.*

class IndexLandsManagerImpl : IndexLandsManager {
    private var translate: Translate? = null

    override fun getTranslate(): Translate {
        return translate!!
    }

    override fun setTranslate(translate: Translate) {
        this.translate = translate
    }

    override fun getLand(landName: String?): Land {
        TODO("Not yet implemented")
    }

    override fun getLand(landID: UUID?): Land {
        TODO("Not yet implemented")
    }

    override fun getLand(landArea: Area?): Land {
        TODO("Not yet implemented")
    }

    override fun createLand(land: Land) {
        val conn = DBManager.storage.getMongoConn()
    }

    class Builder {
        private var manager = IndexLandsManagerImpl()

        fun setTranslate(translate: Translate): Builder {
            manager.setTranslate(translate)
            return this
        }

        fun build(): IndexLandsManager {
            return this.manager
        }

        constructor(plugin: JavaPlugin?)

        constructor(manager: IndexLandsManager) {
            this.manager = manager as IndexLandsManagerImpl
        }
    }
}
