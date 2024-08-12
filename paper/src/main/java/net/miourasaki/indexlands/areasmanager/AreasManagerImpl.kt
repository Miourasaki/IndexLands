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

import net.miourasaki.indexlands.IndexLandsImpl
import net.miourasaki.indexlands.common.IndexImpl
import org.bukkit.Bukkit
import org.bukkit.scheduler.BukkitRunnable
import org.bukkit.scheduler.BukkitTask

class AreasManagerImpl(instance: IndexLandsImpl) : IndexImpl(instance) {

    private val runSecond = ArrayList<BukkitTask>()

    override fun onEnable() {
        runSecond.add(PlayerIntoCheck().runTaskTimer(instance, 0L, 20L))


    }

    override fun onDisable() {
        for (task in runSecond) {
            task.cancel()
        }
    }

}