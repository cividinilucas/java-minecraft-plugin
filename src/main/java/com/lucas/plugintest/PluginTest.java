package com.lucas.plugintest;
import org.bukkit.plugin.java.JavaPlugin;

public final class PluginTest extends JavaPlugin{
//todas as classes que utilizarao eventos devem implementar o listener
    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("Plugin enabled");

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        MuteBlockCommand muteBlockCommand = new MuteBlockCommand();
        BanBlockCommand banBlockCommand = new BanBlockCommand();

        //instancia dos comandos
        getCommand("kick").setExecutor(new KickBlockCommand());
        getCommand("ban").setExecutor(new BanBlockCommand());
        getCommand("config").setExecutor(new ConfigCommand());
        getCommand("ping").setExecutor(new PingBlockCommand());
        getCommand("mute").setExecutor(muteBlockCommand);
        getCommand("unmute").setExecutor(new UnmuteBlockCommand());

        getServer().getPluginManager().registerEvents(muteBlockCommand, this);
        getServer().getPluginManager().registerEvents(banBlockCommand, this);
    }
}
