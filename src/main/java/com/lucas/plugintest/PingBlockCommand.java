package com.lucas.plugintest;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PingBlockCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("Esse comando só pode ser executado por players!");
            return false;
        }

        Player player = (Player) sender;

        int ping = player.getPing();

        player.sendMessage("Seu ping atual é: " + ping);

        return true;
    }
}
