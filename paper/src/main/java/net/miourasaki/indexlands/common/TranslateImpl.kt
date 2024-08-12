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

import me.clip.placeholderapi.PlaceholderAPI
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage
import net.miourasaki.indexlands.IndexLandsImpl
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.entity.Player
import java.io.File
import java.util.regex.Pattern

/**
 * 请在初始化主配置文件后进行初始化
 */
class TranslateImpl (
    private val instance: IndexLandsImpl,
) : Translate {

    override fun initialize() {
        mm = MiniMessage.miniMessage()
        langMap.clear()

        val folder = File(instance.dataFolder, "language")
        if (!(folder.exists() && folder.isDirectory())) {
            initCopy()
            folder.mkdirs()
        }
        val enfile = File(instance.dataFolder, "language/Locate_EN.yml")
        if (!enfile.exists()) this.instance.saveResource("language/Locate_EN.yml", false)
        if (IndexLandsImpl.mainconfig.debug) initCopy()


        for (file in folder.listFiles()!!) {
            val pattern = "locate_([^.]+)\\.yml"
            val r = Pattern.compile(pattern)

            if (file.name.endsWith(".yml")) {
                val m = r.matcher(file.name.lowercase())
                if (m.find() && m.groupCount() >= 1) langMap[m.group(1)] = YamlConfiguration.loadConfiguration(file)

            }
        }

    }

    private fun initCopy() {

        this.instance.saveResource("language/Locate_EN.yml", true)
        this.instance.saveResource("language/Locate_ZH_CN.yml", true)
    }

    private val langMap = HashMap<String, YamlConfiguration>()


    @Suppress("DEPRECATION")
    override fun parseKey(key: String, player: Player?): Component {
        val defaultLang = IndexLandsImpl.mainconfig.lang.getValue()
        if (defaultLang == "local") {
            return Component.translatable(key)
        }else {
            var useLang = defaultLang
            if (player != null) {
                useLang = player.locale
            }
            if (!langMap.containsKey(useLang)) useLang = "en"

            if (IndexLandsImpl.mainconfig.debug) initialize()

            var useTarget = langMap[useLang!!.lowercase()]!!.getString(key)
            if (useTarget == null) useTarget = langMap["en"]!!.getString(key)
            if (useTarget !is String) useTarget = key

            var prefixTarget = langMap[useLang.lowercase()]!!.getString("indexlands.prefix")
            if (prefixTarget == null) prefixTarget = langMap["en"]!!.getString("indexlands.prefix").toString()
            useTarget = useTarget.replace("{prefix}", prefixTarget)

            return parseString(useTarget, player)

        }
    }
    override fun parseString(string: String, player: Player?): Component {
        val result =  PlaceholderAPI.setPlaceholders(player, string)
        return mm.deserialize(result)
    }

    companion object {
        lateinit var mm: MiniMessage
    }
}