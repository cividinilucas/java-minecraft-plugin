package com.lucas.plugintest;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BlockCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (commandSender instanceof Player){
            //forçando ser um player a executar o comando
            Player player = (Player) commandSender;
            player.sendMessage("Your health is restored");
            player.setHealth(20);
        }
        return false;
    }
}
