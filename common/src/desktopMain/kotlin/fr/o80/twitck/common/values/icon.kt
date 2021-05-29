package fr.o80.twitck.common.values

import java.awt.image.BufferedImage
import javax.imageio.ImageIO

val windowIcon: BufferedImage
    get() {
        var image: BufferedImage? = null
        try {
            val iconStream = Thread.currentThread().contextClassLoader.getResource("icon.png")
            image = ImageIO.read(iconStream)
        } catch (e: Exception) {
            // image file does not exist
            e.printStackTrace()
        }

        if (image == null) {
            image = BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB)
        }

        return image
    }
