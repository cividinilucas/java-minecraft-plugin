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

        if (args.length == 0) {
            int playerPing = player.getPing();
            player.sendMessage("Seu ping é de : " + ChatColor.GREEN + playerPing + "ms");
            return true;
        }

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