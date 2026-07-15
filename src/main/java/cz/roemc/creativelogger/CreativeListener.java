package cz.roemc.creativelogger;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCreativeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class CreativeListener implements Listener {

    private final creativelogger plugin;

    public CreativeListener(creativelogger plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onCreativeItemGet(InventoryCreativeEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) return;
        Player player = (Player) event.getWhoClicked();

        ItemStack item = event.getCursor();
        if (item == null || item.getType().isAir()) return;

        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            List<String> lore = meta.hasLore() ? meta.getLore() : new ArrayList<>();

            String format = plugin.getConfig().getString("lore-format", "&7Spawnuto: %player%");
            String formattedLine = format.replace("%player%", player.getName());
            String coloredLine = ChatColor.translateAlternateColorCodes('&', formattedLine);

            if (!lore.contains(coloredLine)) {
                lore.add(coloredLine);
                meta.setLore(lore);
                item.setItemMeta(meta);
            }
        }
    }
}