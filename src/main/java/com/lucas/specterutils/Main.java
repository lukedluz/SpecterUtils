package com.lucas.specterutils;

import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class Main extends JavaPlugin {

    private Map<UUID, UUID> tpa;
    private Map<UUID, Long> cooldown;
    private static hub.tpa.Main main;
    public static net.hubtab.Main getInstance() {
        return net.hubtab.Main.getPlugin(net.hubtab.Main.class);
    }

    @Override
    public void onEnable() {
        Tab.tab.saveDefaultConfig();
        Bukkit.getServer().getPluginManager().registerEvents(this, this);
        Tab.load();
        main = this;
        getServer().getPluginManager().registerEvents(this, this);

        new CooldownCleaner().start();

        tpa = new HashMap<>();
        cooldown = new HashMap<>();

    }

    @Override
    public void onDisable() {
        tpa = null;
        cooldown = null;
        main = null;

        HandlerList.unregisterAll((JavaPlugin) this);

    }
}
