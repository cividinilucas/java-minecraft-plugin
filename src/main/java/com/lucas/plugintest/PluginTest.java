package com.lucas.plugintest;
import com.lucas.plugintest.commands.*;
import com.lucas.plugintest.events.MenuEvents;
import com.lucas.plugintest.events.OnPlayerJoinEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class PluginTest extends JavaPlugin implements Listener{
//todas as classes que utilizarao eventos devem implementar o listener


    Map<UUID, UUID> recentMessages = new HashMap<>();

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("Plugin enabled sucessfully");

        //configs
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        //carregando os hashmaps
        Map<UUID, String> mutedPlayers = new HashMap<>();
        Map<UUID, String> bannedPlayers = new HashMap<>();


        //instancia dos blocos de comandos
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
        getCommand("menu").setExecutor(new MenuBlockCommand());


        //registrando os eventos
        getServer().getPluginManager().registerEvents(muteBlockCommand, this);
        getServer().getPluginManager().registerEvents(banBlockCommand, this);
        getServer().getPluginManager().registerEvents(onPlayerJoinEvent, this);
        Bukkit.getPluginManager().registerEvents(new MenuEvents(), this);
        Bukkit.getPluginManager().registerEvents(this, this);

    }

    public Map<UUID, UUID> getRecentMessages() {
        return recentMessages;
    }

    @EventHandler
    public void onQuitEvent(PlayerQuitEvent event) {
        recentMessages.remove(event.getPlayer().getUniqueId());
    }


}
