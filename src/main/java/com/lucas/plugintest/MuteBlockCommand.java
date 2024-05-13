package com.lucas.plugintest;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MuteBlockCommand implements CommandExecutor, Listener {

    private final Map<UUID, String> mutedPlayers;

    public MuteBlockCommand(Map<UUID, String> mutedPlayers) {
        this.mutedPlayers = mutedPlayers;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Esse comando só pode ser executado por players!");
            return false;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("plugintest.mute")) {
            player.sendMessage("Você não tem permissão!");
            return false;
        }

        if (args.length < 1 || args.length > 2) {
            player.sendMessage("Uso correto: /mute <Jogador> <Motivo>");
            return false;
        }

        Player target = player.getServer().getPlayer(args[0]);


        if (target == null) {
            player.sendMessage(ChatColor.GREEN + "Alvo não encontrado");
            return false;
        }

        UUID uuidTarget = target.getUniqueId();
        String reason = args.length > 1 ? String.join(" ", Arrays.copyOfRange(args, 1, args.length)) : "Sem motivo específico";

        if (isPlayerMuted(uuidTarget)) {
            player.sendMessage(ChatColor.GREEN + "O jogador já está mutado.");
            return false;
        }


        mutedPlayers.put(uuidTarget, reason);
        target.sendMessage(ChatColor.RED + " Você foi mutado por: " + reason);
        player.sendMessage(ChatColor.GREEN + "Você mutou o jogador " + target.getName() + " por: " + reason);

        return true;
    }

    private boolean isPlayerMuted(UUID uuidPlayer) {
        return mutedPlayers.containsKey(uuidPlayer);
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        UUID uuid = event.getPlayer().getUniqueId();
        Player player = event.getPlayer();
        if (isPlayerMuted(uuid)){
            event.setCancelled(true);
            player.sendMessage(ChatColor.RED + "Você está mutado e não pode enviar mensagens.");
        }
    }
}
