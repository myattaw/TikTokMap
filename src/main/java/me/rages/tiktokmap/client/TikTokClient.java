package me.rages.tiktokmap.client;

import io.socket.client.IO;
import io.socket.client.Socket;
import me.rages.tiktokmap.TikTokMap;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.AbstractMap;
import java.util.logging.Level;

/**
 * @author : Michael
 * @since : 5/28/2022, Saturday
 **/
public class TikTokClient {

    /**
     * The user join limit, so we don't have too much data to process
     */
    private static final int EVENT_JOIN_LIMIT = 32;

    /**
     * The main plugin class that handles configurations and commands
     */
    private TikTokMap plugin;

    /**
     * Default constructor for TikTokClient
     * @param plugin
     */
    public TikTokClient(TikTokMap plugin) {
        this.plugin = plugin;
    }

    /**
     * Create a connection to the url using configurations from config.yml
     */
    public void createConnection() {
        try {
            Socket socket = IO.socket(plugin.getConfig().getString("settings.url"));
            socket.open();
            socket.emit("setUniqueId", plugin.getConfig().getString("settings.username"));
            socket.on("like", args -> {
                try {
                    JSONObject json = new JSONObject(String.valueOf(args[0]));
                    Bukkit.broadcastMessage(ChatColor.YELLOW + String.format("%s sent likes to the host", json.getString("uniqueId")));
                    String message = plugin.getConfig().getString("messages.like");
                    plugin.getTikTokEvents().add(new AbstractMap.SimpleEntry<>("§6;" + json.getString("uniqueId"), message));
                } catch (JSONException e) {
                    plugin.getLogger().log(Level.SEVERE, "Error reading json data from like event!");
                }
            }).on("social", args -> {
                try {
                    JSONObject json = new JSONObject(String.valueOf(args[0]));
                    Bukkit.broadcastMessage(ChatColor.YELLOW + String.format("%s sent likes to the host", json.getString("uniqueId")));
                    String message = plugin.getConfig().getString("messages.share");
                    plugin.getTikTokEvents().add(new AbstractMap.SimpleEntry<>("§8;" + json.getString("uniqueId"), message));
                } catch (JSONException e) {
                    plugin.getLogger().log(Level.SEVERE, "Error reading json data from social event!");
                }
            }).on("gift", args -> {
                try {
                    JSONObject json = new JSONObject(String.valueOf(args[0]));
                    Bukkit.broadcastMessage(ChatColor.RED + String.format("%s has gifted a %s", json.getString("uniqueId"), json.getString("giftName")));
                    String message = plugin.getConfig().getString("messages.gift").replace("{count}", String.valueOf(json.getInt("repeatCount"))).replace("{gift}", json.getString("giftName"));
                    plugin.getTikTokEvents().add(new AbstractMap.SimpleEntry<>("§18;" + json.getString("uniqueId"), message));
                } catch (JSONException e) {
                    plugin.getLogger().log(Level.SEVERE, "Error reading json data from gift event!");
                }
            }).on("member", args -> {
                try {
                    JSONObject json = new JSONObject(String.valueOf(args[0]));
                    Bukkit.broadcastMessage(ChatColor.GRAY + String.format("%s has joined the live!", json.getString("uniqueId")));
                    String message = plugin.getConfig().getString("messages.member");
                    if (plugin.getTikTokEvents().size() < EVENT_JOIN_LIMIT) {
                        plugin.getTikTokEvents().add(new AbstractMap.SimpleEntry<>("§12;" + json.getString("uniqueId"), message));
                    }
                } catch (JSONException e) {
                    plugin.getLogger().log(Level.SEVERE, "Error reading json data from member event!");
                }
            });

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

}
