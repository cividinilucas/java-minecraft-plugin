package com.lucas.plugintest;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.CompassMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class OnPlayerJoinEvent implements Listener {


    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
         ItemStack helmet = new ItemStack(Material.LEATHER_HELMET);
         LeatherArmorMeta helmetMeta = (LeatherArmorMeta) helmet.getItemMeta();
         helmetMeta.setColor(Color.GREEN);
         helmet.setItemMeta(helmetMeta);

         event.getPlayer().getInventory().setHelmet(helmet);

         ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
         LeatherArmorMeta chestplateMeta = (LeatherArmorMeta) chestplate.getItemMeta();
         chestplateMeta.setColor(org.bukkit.Color.fromRGB(146, 176, 141));
         chestplate.setItemMeta(chestplateMeta);

         event.getPlayer().getInventory().setChestplate(chestplate);


         ItemStack compass = new ItemStack(Material.COMPASS);
        CompassMeta compassMeta = (CompassMeta) compass.getItemMeta();
        compassMeta.setDisplayName(event.getPlayer().getDisplayName());

        event.getPlayer().getInventory().setItem(4, compass);

         event.setJoinMessage(ChatColor.BLUE + "Olá! Seja muito bem-vindo ao nosso servidor! \n Se divirta!" );
         event.getPlayer().getServer().broadcastMessage(ChatColor.DARK_PURPLE + "O jogador " +
                 event.getPlayer().getDisplayName() +
                 " entrou no servidor!");

    }


}
