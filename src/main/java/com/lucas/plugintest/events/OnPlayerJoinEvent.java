package com.lucas.plugintest.events;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.*;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.CompassMeta;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class OnPlayerJoinEvent implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){

        //setting armor for the player
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
        compassMeta.setLocalizedName(event.getPlayer().getDisplayName());


        ItemStack feather = new ItemStack(Material.FEATHER);

        //setting a compass
        event.getPlayer().getInventory().setItem(4, compass);
        event.getPlayer().getInventory().setItem(1, feather);



        //setting the login message
        event.setJoinMessage(ChatColor.BLUE + "Olá! Seja muito bem-vindo ao nosso servidor! \n Se divirta!" );
        event.getPlayer().getServer().broadcastMessage(
                ChatColor.DARK_PURPLE + "O jogador " +
                 event.getPlayer().getDisplayName() +
                 " entrou no servidor!");


        //setting the screen message
        event.getPlayer().sendTitle(
                ChatColor.BLUE +
                "Bem vindo!",
                org.bukkit.ChatColor.LIGHT_PURPLE +"Chame seus amigos e divirta-se!",
                20,
                100,
                20);

        //custimized tablist
        event.getPlayer().setPlayerListHeaderFooter(org.bukkit.ChatColor.GREEN + "Nicodini RPG", "Se divirta!");

        //fireworks when the player log in
        Firework firework = event.getPlayer().getWorld().spawn(event.getPlayer().getLocation(), Firework.class);
        FireworkMeta fireworkMeta = (FireworkMeta) firework.getFireworkMeta();
        fireworkMeta.addEffect(FireworkEffect.builder().withColor(Color.PURPLE).withColor(Color.RED).with(FireworkEffect.Type.BALL_LARGE).withFlicker().build());
        fireworkMeta.setPower(1);
        firework.setFireworkMeta(fireworkMeta);


        //generate sounds when player login
        event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_GLASS_BREAK, 0.50F, 1F);

    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent interactEvent){

        Player player = interactEvent.getPlayer();

        if(interactEvent.getHand().equals(EquipmentSlot.HAND)){
            if(interactEvent.getAction().equals(Action.RIGHT_CLICK_AIR) || interactEvent.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
                if(player.getInventory().getItemInMainHand() != null && player.getInventory().getItemInMainHand().getType().equals(Material.FEATHER)){
                    if(interactEvent.getPlayer().isFlying() == false){
                        player.sendMessage(ChatColor.GREEN + "Você está voando!");
                        player.setAllowFlight(true);
                    } else if (interactEvent.getPlayer().isFlying() == true) {
                        player.sendMessage(ChatColor.GREEN + "Você não está mais voando!");
                        player.setAllowFlight(false);
                    }
                }
            }
        }

    }

}
