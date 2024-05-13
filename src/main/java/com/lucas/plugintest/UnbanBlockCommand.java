package com.lucas.plugintest;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.UUID;

public class UnbanBlockCommand implements CommandExecutor {

    private final Map<UUID, String> bannedPlayers;

    public UnbanBlockCommand(Map<UUID, String> bannedPlayers) {
        this.bannedPlayers = bannedPlayers;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if(!(sender instanceof Player)){
            sender.sendMessage("O comando só pode ser executado por jogadores");
            return false;
        }

        Player player = (Player) sender;

        if(!player.hasPermission("plugintest.unban")){
            player.sendMessage("Você não tem permissão para executar esse comando!");
            return false;
        }

        if(args.length < 1){
            player.sendMessage("Uso correto: /unban <Jogador>");
            return false;
        }

        Player target = Bukkit.getPlayer(args[0]);

        if(target == null){
            player.sendMessage("Jogador não encontrado!");
            return false;
        }

        UUID uuidTarget = target.getUniqueId();

        if(!isPlayerBanned(uuidTarget)){
            player.sendMessage("Jogador não está banido!");
            return false;
        }

        bannedPlayers.remove(uuidTarget);
        player.sendMessage("Você desbaniu com sucesso o jogador " + target.getName());

        return true;
    }

    public boolean isPlayerBanned(UUID uuidPlayer){
        return bannedPlayers.containsKey(uuidPlayer);
    }

}
