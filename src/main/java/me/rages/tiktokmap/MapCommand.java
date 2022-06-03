package me.rages.tiktokmap;

import io.socket.client.IO;
import io.socket.client.Socket;
import me.rages.tiktokmap.client.TikTokClient;
import me.rages.tiktokmap.renderer.TikTokRenderer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.MapMeta;
import org.bukkit.map.MapView;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.AbstractMap;

/**
 * @author : Michael
 * @since : 5/24/2022, Tuesday
 **/
public class MapCommand implements CommandExecutor {

    private static final String NOT_A_PLAYER_ERROR = ChatColor.RED + "You must be a player to perform this command!";

    private TikTokMap plugin;
    private TikTokClient client;

    public MapCommand(TikTokMap plugin) {
        this.plugin = plugin;
        this.client = new TikTokClient(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            ItemStack itemStack = new ItemStack(Material.FILLED_MAP);
            MapMeta mapMeta = (MapMeta) itemStack.getItemMeta();

            Player player = (Player) sender;
            MapView mapView = Bukkit.createMap(player.getWorld());
            client.createConnection();
            mapView.addRenderer(new TikTokRenderer(plugin));
            mapMeta.setMapView(mapView);
            itemStack.setItemMeta(mapMeta);

            player.getInventory().addItem(itemStack);
        } else {
            sender.sendMessage(NOT_A_PLAYER_ERROR);
        }

        return false;
    }

}
