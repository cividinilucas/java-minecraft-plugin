package com.lucas.plugintest;

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

<<<<<<< HEAD
        int playerPing = player.getPing();

            if(playerPing <= 100) {
                player.sendMessage("Seu ping é de : " + ChatColor.GREEN + playerPing + "ms");
            } else if (playerPing <= 200) {
                player.sendMessage("Seu ping é de: " + ChatColor.RED + playerPing + "ms");
            } else {
                player.sendMessage("Seu ping é de: " + ChatColor.DARK_RED + playerPing + "ms");
            }

=======
        if (args.length == 0) {
            int playerPing = player.getPing();
            if(playerPing <= 100) {
                player.sendMessage("Seu ping é de : " + ChatColor.GREEN + playerPing + "ms");
            } else if (playerPing > 100 && playerPing <= 200) {
                player.sendMessage("Seu ping é de: " + ChatColor.RED + playerPing + "ms");
            } else if (playerPing > 200) {
                player.sendMessage("Seu ping é de: " + ChatColor.DARK_RED + playerPing + "ms");
            }

            return true;
        }

>>>>>>> 4a324cbdbb5284a2856e5e74d545b44f2378af90
        Player target = player.getServer().getPlayer(args[0]);

        if (target == null) {
            player.sendMessage( "Jogador não encontrado");
            return false;
        }

        int targetPing = target.getPing();
        player.sendMessage("O ping do jogador " + target.getDisplayName() + " é de: " + ChatColor.GREEN + targetPing + "ms");
        return true;
    }
}