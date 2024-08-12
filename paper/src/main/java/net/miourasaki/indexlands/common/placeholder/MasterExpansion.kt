package net.miourasaki.indexlands.common.placeholder

import me.clip.placeholderapi.expansion.PlaceholderExpansion
import net.miourasaki.indexlands.IndexLandsImpl
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.scheduler.BukkitRunnable
import java.util.regex.Pattern


class MasterExpansion(//
    private var plugin: IndexLandsImpl
) : PlaceholderExpansion() {


    override fun getAuthor(): String {
        return java.lang.String.join(", ", plugin.description.authors)
    }
    override fun getVersion(): String {
        return plugin.description.version
    }

    override fun getIdentifier(): String {
        return "iland"
    }

    override fun persist(): Boolean {
        return true //
    }

    override fun onPlaceholderRequest(player: Player, params: String): String? {
        if (onlineExpansionMap.isEmpty()) return null
        for (key in onlineExpansionMap.keys) {
            val pattern = Pattern.compile(key)
            val matcher = pattern.matcher(params)

            if (matcher.find()) {
                var content = ""
                if (matcher.groupCount() > 0) content = matcher.group(1)
                return onlineExpansionMap[key]!!.onRequest(player, content, params)
            }
        }
        return null;
    }

    companion object {
        var onlineExpansionMap = mutableMapOf<String, IndexRequest>()
        fun registerOnlineRequest(re:String, ex: IndexRequest) {
            onlineExpansionMap[re] = ex
        }
        fun onEnable() {
            val secondRun: BukkitRunnable = object : BukkitRunnable() {
                override fun run() {
                    if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
                        MasterExpansion(IndexLandsImpl.instance).register()
                        this.cancel()
                    }
                }
            }
            secondRun.runTaskTimer(IndexLandsImpl.instance, 0, 1)
        }
        fun onDisable() {
            // Plugin shutdown logic
            if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
                MasterExpansion(IndexLandsImpl.instance).unregister()
            }
        }
    }
}
