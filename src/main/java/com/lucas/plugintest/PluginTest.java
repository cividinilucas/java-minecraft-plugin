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


        //instancia dos comandos
        getCommand("kick").setExecutor(new KickBlockCommand());
        getCommand("ban").setExecutor(new BanBlockCommand());
        getCommand("config").setExecutor(new ConfigCommand());
        getCommand("ping").setExecutor(new PingBlockCommand());
        getCommand("mute").setExecutor(new MuteBlockCommand());

        getServer().getPluginManager().registerEvents(new MuteBlockCommand(), this);
    }
}
