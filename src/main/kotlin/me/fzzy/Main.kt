package me.fzzy

import org.json.JSONException
import org.json.JSONObject
import java.io.File
import javax.imageio.ImageIO
import javax.swing.*

val activePlayers = hashMapOf<String, ActiveApexPlayer>()
val windowSize = 720

fun main(args: Array<String>) {
    val mapframe = JFrame()

    val map = MapPainter()
    mapframe.add(map)

    mapframe.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
    mapframe.pack()
    mapframe.setSize(windowSize, windowSize)
    mapframe.isVisible = true

    Thread {
        val reader = File("C:\\Users\\Fzzy\\Desktop\\test.json").bufferedReader()
        while (true) {
            val line = reader.readLine()
            if (line != null) {
                if (line.startsWith("[") || line.startsWith("]")) continue

                val obj = JSONObject("${if (line.startsWith("{\"")) "" else "{\""}$line")
                try {
                    val player = obj.getJSONObject("player")
                    val hash = player.getString("nucleusHash")
                    if (!activePlayers.containsKey(hash)) {
                        val activePlayer = ActiveApexPlayer()
                        activePlayer.ingestData(obj)
                        activePlayers[hash] = activePlayer
                    } else {
                        activePlayers[hash]!!.ingestData(obj)
                    }
                    mapframe.repaint()
                    Thread.sleep(10)
                } catch (e :JSONException) {

                }
            }
        }
    }.start()
}