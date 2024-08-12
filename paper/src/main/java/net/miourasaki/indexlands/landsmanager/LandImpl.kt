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

package net.miourasaki.indexlands.landsmanager

import net.miourasaki.indexlands.land.Land
import org.bukkit.entity.Player
import java.util.*

class LandImpl : Land {

    private lateinit var uuid: UUID
    private lateinit var name: String

    override fun getUniqueId(): UUID {
        return uuid;
    }

    override fun getName(): String {
        return name;
    }

    override fun setName(name: String) {
        this.name = name
    }

    override fun getOwnerPlayer(): Player {
        TODO("Not yet implemented")
    }

    override fun setOwnerPlayer(player: Player?) {
        TODO("Not yet implemented")
    }
}