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
            skin = player.getString("skin")
            name = player.getString("name")
            teamName = player.getString("teamName")
            character = player.getString("character")

            nucleusHash = json.getString("nucleusHash")

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