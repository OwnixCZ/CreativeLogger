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

    private final Creativelogger plugin;

    public CreativeListener(Creativelogger plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onCreativeItemGet(InventoryCreativeEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) return;
        Player player = (Player) event.getWhoClicked();

        // Získáme předmět, který má hráč na kurzoru
        ItemStack item = event.getCursor();
        if (item == null || item.getType().isAir()) return;

        // Debug zpráva do chatu pro jistotu, že event funguje
        player.sendMessage(ChatColor.GREEN + "[CreativeLogger Debug] Klikl jsi na: " + item.getType());

        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            List<String> lore = meta.hasLore() ? meta.getLore() : new ArrayList<>();

            // Načtení formátu z configu
            String format = plugin.getConfig().getString("lore-format", "&7Spawnuto: &e%player%");
            String formattedLine = format.replace("%player%", player.getName());
            String coloredLine = ChatColor.translateAlternateColorCodes('&', formattedLine);

            // Pokud předmět ještě tento lore nemá, přidáme ho
            if (!lore.contains(coloredLine)) {
                lore.add(coloredLine);
                meta.setLore(lore);
                item.setItemMeta(meta);

                // Nastavíme upravený item zpět na kurzor
                event.setCursor(item);
                player.sendMessage(ChatColor.YELLOW + "[CreativeLogger Debug] Lore byl uspesne nastaven!");
            }
        }
    }
}