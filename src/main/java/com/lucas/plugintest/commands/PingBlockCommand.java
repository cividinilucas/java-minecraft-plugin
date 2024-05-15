package com.lucas.plugintest.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PingBlockCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        
        if (!(sender instanceof Player)) {
            sender.sendMessage("Esse comando só pode ser executado por players!");
            return false;
        }

        Player player = (Player) sender;


        int playerPing = player.getPing();

        if (args.length == 0) {
            if(playerPing <= 100) {
                player.sendMessage("Seu ping é de : " + ChatColor.GREEN + playerPing + "ms");
            } else if (playerPing > 100 && playerPing <= 200) {
                player.sendMessage("Seu ping é de: " + ChatColor.RED + playerPing + "ms");
            } else if (playerPing > 200) {
                player.sendMessage("Seu ping é de: " + ChatColor.DARK_RED + playerPing + "ms");
            }

            return true;
        }

        Player target = player.getServer().getPlayer(args[0]);

        if (target == null) {
            player.sendMessage( "Jogador não encontrado");
            return false;
        }

        int targetPing = target.getPing();

        if (targetPing <= 100) {
            player.sendMessage("O ping do jogador " + target.getName() + ChatColor.GREEN + " é de: " + targetPing + " ms");
        } else if (playerPing > 100 && playerPing <=200) {
            player.sendMessage("O ping do jogador " + target.getName() + ChatColor.RED + " é de: " + targetPing + " ms");
        }else{
            player.sendMessage("O ping do jogador " + target.getName() + ChatColor.DARK_RED + " é de: " + targetPing + " ms");
        }
        return true;
    }
}