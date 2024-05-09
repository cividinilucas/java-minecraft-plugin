package com.lucas.plugintest;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public class MuteBlockCommand implements CommandExecutor, Listener {

    private final Map<Player, String> mutedPlayers = new HashMap<>();

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
            player.sendMessage("Alvo não encontrado");
            return false;
        }

        String reason = args.length > 1 ? String.join(" ", args[1]) : "Sem motivo específico";

     /*  if (isPlayerMuted(target)) {
            player.sendMessage("O jogador já está mutado.");
            return false;
        }*/

        mutedPlayers.put(target, reason);
        player.sendMessage("Você mutou o jogador " + target.getName() + " por: " + reason);

        return true;
    }

    private boolean isPlayerMuted(Player player) {
        return mutedPlayers.containsKey(player);
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        if (isPlayerMuted(player)){
            System.out.println("got to event cancel");
            event.setCancelled(true);
            player.sendMessage("Você está mutado e não pode enviar mensagens.");
        }
    }
}
