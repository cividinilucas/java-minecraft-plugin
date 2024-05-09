package com.lucas.plugintest;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.*;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginLoader;

import java.io.File;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringJoiner;
import java.util.logging.Logger;

import static org.bukkit.Bukkit.getServer;

public class MuteBlockCommand implements CommandExecutor, Listener{

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if(!(sender instanceof Player)){
            sender.sendMessage("Esse comando só pode ser executado por players!");
            return false;
        }

        Player player = (Player) sender;

        if(!player.hasPermission("plugintest.mute")){
            player.sendMessage("Você não tem permissão!");
            return false;
        }

        if(args.length < 1 || args.length > 2){
            player.sendMessage("Uso correto: /mute <Jogador> <Motivo>");
        }

        Player target = player.getServer().getPlayer(args[0]);

        if(target == null){
            player.sendMessage("Alvo não encontrado");
        }

        target.getServer().getPluginManager().registerEvents(this, (Plugin) this);

        String reason = "Sem motivo específico";
        if(args.length > 1){
            reason = String.join(" ", Arrays.copyOfRange(args, 1, args.length));
        }
        return true;
    }



    private boolean isPlayerMuted(Player player) {
        return false;
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event){
        if(isPlayerMuted(event.getPlayer())){
                event.setCancelled(true);
                event.getPlayer().sendMessage("Você está mutado!");
        }
    }

}
