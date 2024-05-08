package com.lucas.plugintest;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerEggThrowEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class PluginTest extends JavaPlugin{
//todas as classes que utilizarao eventos devem implementar o listener
    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("Plugin enabled");

        getConfig().options().copyDefaults();
        saveDefaultConfig();


        getCommand("kick").setExecutor(new KickBlockCommand());
        getCommand("ban").setExecutor(new BanBlockCommand());
        getCommand("config").setExecutor(new ConfigCommand());

    }
}
