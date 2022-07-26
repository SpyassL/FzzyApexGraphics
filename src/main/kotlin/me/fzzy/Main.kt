package me.fzzy

import org.json.JSONObject
import java.io.File
import javax.swing.JFrame
import javax.swing.JSlider

fun main(args: Array<String>) {
    val reader = File("C:\\Users\\Admin\\Desktop\\test.json").bufferedReader()
    while (true) {
        val line = reader.readLine()
        if (line != null) {
            if (line.startsWith("[") || line.startsWith("]")) continue
            println(line)

            val obj = JSONObject("${if (line.startsWith("{\"")) "" else "{\""}$line")
        }
    }

    /*val frame = JFrame()
    val button = JSlider()
    button.setBounds(130, 100, 100, 40)
    frame.add(button)
    frame.setSize(400, 500)
    frame.layout = null
    frame.isVisible = true*/
}