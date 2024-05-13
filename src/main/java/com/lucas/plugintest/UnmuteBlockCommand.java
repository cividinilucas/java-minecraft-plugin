package com.lucas.plugintest;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;

import java.util.Map;
import java.util.UUID;


public class UnmuteBlockCommand implements CommandExecutor {

    private final Map<UUID, String> mutedPlayers;

    public UnmuteBlockCommand(Map<UUID, String> mutedPlayers) {
        this.mutedPlayers = mutedPlayers;
    }

    public Map<UUID, String> getMutedPlayers() {
        return mutedPlayers;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("Esse comando só pode ser executado por players!");
            return false;
        }

        Player player = (Player) sender;

        if(!sender.hasPermission("plugintest.unmute")) {

            player.sendMessage("Você não tem permissão");
            return false;
        }

        if (args.length < 1) {
            player.sendMessage("Uso correto: /unmute <Jogador>");
            return false;
        }

        Player target = player.getServer().getPlayer(args[0]);

        if (target == null) {
            player.sendMessage("Jogador não encontrado ou não está mutado");
            return false;
        }

        UUID uuidTarget = target.getUniqueId();

        mutedPlayers.remove(uuidTarget);
        target.sendMessage(ChatColor.GREEN + "Você foi desmutado!");

        return true;
    }

    public boolean isPlayerMuted(UUID uuidPlayer){
        return mutedPlayers.containsKey(uuidPlayer);
    }

}
