package com.lucas.plugintest;

import org.bukkit.BanList;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.material.Colorable;

import javax.swing.*;
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


        //se argumentos forem menor que um, quer dizer que comando foi executado de forma incorreta
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


        //se os arguments forem > 1, significa que a motivo, senao ele nao tera e subira a msg padrao da var
        if(args.length > 1){
           reason = String.join(" ", Arrays.copyOfRange(args, 1, args.length));
        }

        target.getServer().getBanList(BanList.Type.NAME).addBan(target.getName(), reason, null, player.getName());
        target.kickPlayer(ChatColor.DARK_RED + "Você foi banido!" +  "\n Motivo: " + reason + "\nBanido por: " + player.getDisplayName());
        player.sendMessage("Você baniu o jogador " + target.getDisplayName() + "Pelo motivo: " + reason);

        return true;
    }

}
