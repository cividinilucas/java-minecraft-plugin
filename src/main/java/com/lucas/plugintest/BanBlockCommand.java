package com.lucas.plugintest;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class BanBlockCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)){
            sender.sendMessage("O comando só pode ser executado por jogadores");
            return false;
        }

        Player player = (Player) sender;

        //testando a permissao
        if(!player.hasPermission("plugintest.kick")){
            player.sendMessage("Você não tem permissão");
            return false;
        }

        if(args.length < 1){
            player.sendMessage("Uso correto: /ban <Jogador> <Motivo>");
            return false;
        }

        //logica para pegar o destinatario do comando
        Player target = player.getServer().getPlayer(args[0]);

        if(target == null){
            player.sendMessage("Jogador não encontrado");
            return false;
        }

        String reason = "Sem motivo específicado";

        if(args.length > 1){
           reason = String.join(" ", Arrays.copyOfRange(args, 1, args.length));
        }

        target.kickPlayer("Você foi banido por: " + player.getDisplayName() + "Pelo motivo: " + reason);
        player.sendMessage("Você baniu o jogador " + target.getDisplayName() + "Pelo motivo: " + reason);

        return true;
    }



}
