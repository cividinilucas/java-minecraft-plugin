package com.lucas.plugintest;
import com.lucas.plugintest.commands.*;
import com.lucas.plugintest.events.OnPlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class PluginTest extends JavaPlugin{
//todas as classes que utilizarao eventos devem implementar o listener
    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("Plugin enabled sucessfully");

        //configs
        getConfig().options().copyDefaults();
        saveDefaultConfig();


        Map<UUID, String> mutedPlayers = new HashMap<>();
        Map<UUID, String> bannedPlayers = new HashMap<>();

        MuteBlockCommand muteBlockCommand = new MuteBlockCommand(mutedPlayers);
        BanBlockCommand banBlockCommand = new BanBlockCommand(bannedPlayers);
        OnPlayerJoinEvent onPlayerJoinEvent = new OnPlayerJoinEvent();

        //instancia dos comandos
        getCommand("kick").setExecutor(new KickBlockCommand());
        getCommand("ban").setExecutor(banBlockCommand);
        getCommand("config").setExecutor(new ConfigCommand());
        getCommand("ping").setExecutor(new PingBlockCommand());
        getCommand("mute").setExecutor(muteBlockCommand);
        getCommand("unmute").setExecutor(new UnmuteBlockCommand(mutedPlayers));
        getCommand("unban").setExecutor(new UnbanBlockCommand(bannedPlayers));

        getServer().getPluginManager().registerEvents(muteBlockCommand, this);
        getServer().getPluginManager().registerEvents(banBlockCommand, this);
        getServer().getPluginManager().registerEvents(onPlayerJoinEvent, this);

    }
}
