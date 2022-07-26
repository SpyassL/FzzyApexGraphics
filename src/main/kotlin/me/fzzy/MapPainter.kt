package me.fzzy

import java.awt.Canvas
import java.awt.Color
import java.awt.Graphics
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

class MapPainter: Canvas() {

    override fun paint(g: Graphics) {
        val map = ImageIO.read(File("C:\\Users\\Fzzy\\Desktop\\worlds_edge.jpg"))
        val dot = ImageIO.read(File("C:\\Users\\Fzzy\\Desktop\\dot.png"))
        //tint(i)
        g.drawImage(map, 0, 0, 720, 720,this)
        g.drawImage(dot, 100, 250, 15,15, this)
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