package cz.roemc.creativelogger;

import org.bukkit.plugin.java.JavaPlugin;

public final class creativelogger extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new CreativeListener(this), this);
        getLogger().info("CreativeLogger byl uspesne aktivovan!");
    }

    @Override
    public void onDisable() {
        getLogger().info("CreativeLogger byl uspesne vypnut!");
    }
}