package me.rages.tiktokmap;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.LinkedList;
import java.util.Map;

public final class TikTokMap extends JavaPlugin {

    /**
     * An ordered list storing TikTok events from the user
     */
    private LinkedList<Map.Entry<String, String>> tiktokEvents = new LinkedList<>();

    @Override
    public void onEnable() {
        saveDefaultConfig();
        getCommand("tiktokmap").setExecutor(new MapCommand(this));
    }

    /**
     * Get the list of TikTok events from the users livestream
     * @return a list of TikTok events retrieved from the websocket
     */
    public LinkedList<Map.Entry<String, String>> getTikTokEvents() {
        return tiktokEvents;
    }
}
