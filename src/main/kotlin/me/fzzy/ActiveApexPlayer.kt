package me.fzzy

import me.fzzy.util.Vector3
import org.json.JSONException
import org.json.JSONObject

class ActiveApexPlayer {

    class PlayerPosition(val timestamp: Long, val position: Vector3, val angle: Vector3)

    val positions: ArrayList<PlayerPosition> = arrayListOf()

    var skin: String = ""
    var character: String = ""
    var teamName: String = ""
    var name: String = ""

    var nucleusHash: String = ""

    fun ingestData(json: JSONObject) {
        try {
            val player = json.getJSONObject("player")
            if (player.has("skin")) skin = player.getString("skin")
            if (player.has("name")) name = player.getString("name")
            if (player.has("teamName")) teamName = player.getString("teamName")
            if (player.has("character")) character = player.getString("character")

            if (player.has("nucleusHash")) nucleusHash = player.getString("nucleusHash")

            val timestamp = json.getLong("timestamp")
            val jsonPosition = player.getJSONObject("pos")
            val position = Vector3(jsonPosition.getFloat("x"), jsonPosition.getFloat("y"), jsonPosition.getFloat("z"))

            val jsonAngle = player.getJSONObject("angles")
            val angle = Vector3(jsonAngle.getFloat("x"), jsonAngle.getFloat("y"), jsonAngle.getFloat("z"))

            positions.add(PlayerPosition(timestamp, position, angle))

            positions.sortBy { it.timestamp }
        }catch (_: JSONException) {
        }
    }

}