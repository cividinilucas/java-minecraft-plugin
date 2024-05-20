package com.lucas.plugintest.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.Random;

public class MenuEvents implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e){

        if(ChatColor.translateAlternateColorCodes('&', e.getView().getTitle()).equals(ChatColor.RED.toString() + ChatColor.BOLD + "Tool menu!")
            && e.getCurrentItem() != null){
            Player player = (Player) e.getWhoClicked();
                switch (e.getRawSlot()){
                    case 0:
                        break;
                    case 20: //random tp
                        Random random = new Random();
                        player.teleport((Player) Bukkit.getOnlinePlayers().toArray()[random.nextInt(Bukkit.getOnlinePlayers().size())]);
                        player.sendMessage(ChatColor.RED + "You teleported to a random player");

                        break;
                    case 22: //kys

                        player.setHealth(0);
                        player.sendMessage(ChatColor.RED + "You killed yourself");

                        break;
                    case 24: //clear inv
                        player.closeInventory();
                        player.getInventory().clear();
                        player.sendMessage(ChatColor.RED + "You cleared your inventory");
                        return;

                    default:
                        return;
                }

            player.closeInventory();
        }


    }


}
