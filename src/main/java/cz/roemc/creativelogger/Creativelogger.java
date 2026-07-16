package cz.roemc.creativelogger;

import org.bukkit.plugin.java.JavaPlugin;

public final class Creativelogger extends JavaPlugin {

    @Override
    public void onEnable() {
        // Uloží výchozí config.yml, pokud neexistuje
        saveDefaultConfig();

        // Zaregistruje event listener
        getServer().getPluginManager().registerEvents(new CreativeListener(this), this);

        getLogger().info("CreativeLogger byl uspesne aktivovan!");
    }

    @Override
    public void onDisable() {
        getLogger().info("CreativeLogger byl uspesne vypnut!");
    }
}