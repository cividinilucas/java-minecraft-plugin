package com.lucas.plugintest;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class PluginTest extends JavaPlugin{
//todas as classes que utilizarao eventos devem implementar o listener
    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("Plugin enabled");

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        Map<UUID, String> mutedPlayers = new HashMap<>();

        MuteBlockCommand muteBlockCommand = new MuteBlockCommand(mutedPlayers);
        BanBlockCommand banBlockCommand = new BanBlockCommand();


        //instancia dos comandos
        getCommand("kick").setExecutor(new KickBlockCommand());
        getCommand("ban").setExecutor(banBlockCommand);
        getCommand("config").setExecutor(new ConfigCommand());
        getCommand("ping").setExecutor(new PingBlockCommand());
        getCommand("mute").setExecutor(muteBlockCommand);
        getCommand("unmute").setExecutor(new UnmuteBlockCommand(mutedPlayers));


        getServer().getPluginManager().registerEvents(muteBlockCommand, this);
        getServer().getPluginManager().registerEvents(banBlockCommand, this);


    }
}
