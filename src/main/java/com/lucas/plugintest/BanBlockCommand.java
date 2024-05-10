package com.lucas.plugintest;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class BanBlockCommand implements CommandExecutor, Listener {

    private final Map<UUID, String> banLists = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)){
            sender.sendMessage("O comando só pode ser executado por jogadores");
            return false;
        }

        Player player = (Player) sender;

        //testando a permissao
        if(!player.hasPermission("plugintest.ban")){
            player.sendMessage("Você não tem permissão");
            return false;
        }

        //se argumentos forem menor que um, quer dizer que comando foi executado de forma incorreta
        if(args.length < 1 || args.length > 2){
            player.sendMessage("Uso correto: /ban <Jogador> <Motivo>");
            return false;
        }

        //logica para pegar o destinatario do comando
        Player target = Bukkit.getPlayer(args[0]);

        if(target == null){
            player.sendMessage("Jogador não encontrado.");
        }

        UUID targetUUID = target.getUniqueId();
        String reason = args.length > 1 ? String.join(" ", Arrays.copyOfRange(args, 1, args.length)) : "Sem motivo específico";

        if(isPlayerBanned(targetUUID)){
            player.sendMessage("O jogador já está banido!");
            return false;
        }

        banLists.put(targetUUID, reason);
        target.kickPlayer(ChatColor.DARK_RED + "Você foi banido!" +  "\n Motivo: " + reason + "\nBanido por: " + player.getDisplayName());
        player.sendMessage("Você baniu o jogador " + target);

        return true;
    }

    public boolean isPlayerBanned(UUID playerUUID){
        return banLists.containsKey(playerUUID);
    }
    @EventHandler
    public void onPlayerLogin(AsyncPlayerPreLoginEvent e){
        UUID uuid = e.getUniqueId();

        if(isPlayerBanned(uuid)){
            System.out.println("reach");
            e.disallow(AsyncPlayerPreLoginEvent.Result.KICK_BANNED, "banido!");
        }
    }
}
