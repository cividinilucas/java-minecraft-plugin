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

<<<<<<< HEAD
        Map<UUID, String> mutedPlayers = new HashMap<>();

        MuteBlockCommand muteBlockCommand = new MuteBlockCommand(mutedPlayers);
        BanBlockCommand banBlockCommand = new BanBlockCommand();


=======
        MuteBlockCommand muteBlockCommand = new MuteBlockCommand();
        BanBlockCommand banBlockCommand = new BanBlockCommand();

>>>>>>> 4a324cbdbb5284a2856e5e74d545b44f2378af90
        //instancia dos comandos
        getCommand("kick").setExecutor(new KickBlockCommand());
        getCommand("ban").setExecutor(banBlockCommand);
        getCommand("config").setExecutor(new ConfigCommand());
        getCommand("ping").setExecutor(new PingBlockCommand());
        getCommand("mute").setExecutor(muteBlockCommand);
<<<<<<< HEAD
        getCommand("unmute").setExecutor(new UnmuteBlockCommand(mutedPlayers));


        getServer().getPluginManager().registerEvents(muteBlockCommand, this);
        getServer().getPluginManager().registerEvents(banBlockCommand, this);

=======
        getCommand("unmute").setExecutor(new UnmuteBlockCommand());

        getServer().getPluginManager().registerEvents(muteBlockCommand, this);
        getServer().getPluginManager().registerEvents(banBlockCommand, this);
>>>>>>> 4a324cbdbb5284a2856e5e74d545b44f2378af90

    }
}
