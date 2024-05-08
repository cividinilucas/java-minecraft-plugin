package com.lucas.plugintest;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class KickBlockCommand implements CommandExecutor{

        @Override
        public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

            //verificar se o executator is player
            if(!(sender instanceof Player)){
                sender.sendMessage("Só pode ser executado por jogadores");
                return false;
            }

            Player player = (Player) sender;

            if(!player.hasPermission("plugintest.kick")){
                player.sendMessage("Voce nao tem permissao");
                return false;
            }

            //verificar se numero correto do arg foi fornecido
            if(args.length < 1){
                player.sendMessage("Uso correto: /kick <jogador> <motivo>");
                return false;
            }

            //obter jogador a ser expulso
            //criar var para tager e pegar o servidor > player
            Player target = player.getServer().getPlayer(args[0]);

            if(target == null){
                player.sendMessage("O jogador não está online!");
                return false;
            }

            //criar variavel pro motivo
            String reason = "Sem motivo especificado";
            if(args.length > 1){
            reason = String.join(" ", Arrays.copyOfRange(args, 1, args.length));
            }

            //expulsar
            target.kickPlayer("voce foi expulso pelo " + player.getDisplayName() + " pelo motivo: " + reason);
            player.sendMessage("Voce kickou o jogador " + target.getName());
            return true;        }
}


