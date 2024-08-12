/*
 *  Copyright (C) <2024> Miourasaki<mio@chyan.moe>
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package net.miourasaki.indexlands

import io.dev_m.indexlands.DevmioImpl
import io.dev_m.indexlands.bstats.Metrics
import net.miourasaki.indexlands.areasmanager.AreasManagerImpl
import net.miourasaki.indexlands.command.CommandImpl
import net.miourasaki.indexlands.command.NeofetchCommand
import net.miourasaki.indexlands.common.IndexImpl
import net.miourasaki.indexlands.common.TranslateImpl
import net.miourasaki.indexlands.common.configuration.MasterConfig
import net.miourasaki.indexlands.common.placeholder.MasterExpansion
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import java.util.concurrent.ConcurrentLinkedDeque

class IndexLandsImpl : JavaPlugin() {

    private val implList = ConcurrentLinkedDeque<IndexImpl>()
    private lateinit var manager: IndexLandsManager

    override fun onLoad() {
        // Plugin load logic
        instance = this
        mainconfig = MasterConfig(this)

        this.manager = IndexLandsManagerImpl.Builder(this)
            .setTranslate(TranslateImpl(this))
            .build()


        this.implList.add(DevmioImpl(this))
        this.implList.add(AreasManagerImpl(this))
        this.implList.add(CommandImpl(this))
    }

    override fun onEnable() {
        // Plugin startup logic
        this.manager.translate.initialize()
        mainconfig.initialize(); if (mainconfig.enable) {
            IndexLands.setManager(manager)
            NeofetchCommand.run(Bukkit.getConsoleSender())

            MasterExpansion.onEnable()
            for (impl in implList) {
                impl.onEnable()
            }
        }

        // bStats code
        val metrics = Metrics(this, PLUGIN_ID)
    }
    override fun onDisable() {
        // Plugin shutdown logic
        MasterExpansion.onDisable()
        for (impl in implList) {
            impl.onDisable()
        }
    }

    companion object {
        lateinit var instance: IndexLandsImpl
        lateinit var mainconfig: MasterConfig
        const val PLUGIN_ID = 22973
    }
}
