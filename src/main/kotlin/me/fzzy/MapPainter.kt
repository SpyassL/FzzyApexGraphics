package me.fzzy

import java.awt.Canvas
import java.awt.Color
import java.awt.Graphics
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
import javax.swing.JPanel
import kotlin.math.roundToInt

class MapPainter : JPanel() {

    val mapSize = 46000

    override fun paintComponent(g: Graphics) {
        super.paintComponent(g)
        val map = ImageIO.read(File("C:\\Users\\Fzzy\\Desktop\\worlds_edge.jpg"))
        val dot = ImageIO.read(File("C:\\Users\\Fzzy\\Desktop\\dot.png"))
        //tint(i)
        g.drawImage(map, 0, 0, 720, 720, this)
        for ((hash, player) in activePlayers) {
            val pos = player.positions[player.positions.size - 1].position
            val x = ((pos.x + mapSize) / (mapSize * 2)) * windowSize
            val y = ((-pos.y + mapSize) / (mapSize * 2)) * windowSize
            g.drawImage(dot, x.roundToInt(), y.roundToInt(), 15, 15, this)
        }
        g.dispose()
    }

    fun tint(img: BufferedImage) {
        for (x in 0 until img.width) {
            for (y in 0 until img.height) {
                val color = Color(img.getRGB(x, y))

                img.setRGB(x, y, color.brighter().rgb)
            }
        }
    }
}