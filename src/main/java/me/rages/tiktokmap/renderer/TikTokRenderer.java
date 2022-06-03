package me.rages.tiktokmap.renderer;

import me.rages.tiktokmap.TikTokMap;
import org.bukkit.entity.Player;
import org.bukkit.map.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.Map;

/**
 * @author : Michael
 * @since : 5/24/2022, Tuesday
 **/
public class TikTokRenderer extends MapRenderer {

    private static final int PADDING = 1;
    private static final int MAP_SIZE = 128;
    private static final int MAX_TEXT_LINES = 12;

    private final TikTokMap plugin;
    private MinecraftFont font;
    private Image backgroundImage;

    public TikTokRenderer(TikTokMap plugin) {
        this.plugin = plugin;
        this.font = new MinecraftFont();
        try {
            URL url = new URL(this.plugin.getConfig().getString("settings.background"));
            this.backgroundImage = ImageIO.read(url);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void render(MapView map, MapCanvas canvas, Player player) {
        // draw the background image for the map
        canvas.drawImage(0, 0, MapPalette.resizeImage(backgroundImage));

        int index = 0;
        int y = PADDING; // add some space so the text fits on the screen

        while (index < 12 && index < plugin.getTikTokEvents().size()) {
            Map.Entry<String, String> user = plugin.getTikTokEvents().get(index);
            drawCenteredString(canvas, y + PADDING, user.getKey());
            y += font.getHeight() + PADDING; // add font height + space to make the map look clean
            index++;
        }

        if (plugin.getTikTokEvents().size() > MAX_TEXT_LINES) { // check if the map is filled up with text
            plugin.getTikTokEvents().pop(); // remove the oldest text from the map
        }


    }

    private void drawCenteredString(MapCanvas canvas, int y, String str) {
        if (str != null) {
            canvas.drawText((MAP_SIZE / 2) - (font.getWidth(str) / 2), y, font, str);
        }
    }

}
