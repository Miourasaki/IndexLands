package net.miourasaki.indexlands.common.placeholder

import org.bukkit.entity.Player

interface IndexRequest {

    fun onRequest(player: Player, params: String, original:String): String?

}