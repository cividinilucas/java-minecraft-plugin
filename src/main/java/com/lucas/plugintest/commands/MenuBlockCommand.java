package com.lucas.plugintest.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class MenuBlockCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        //verificar se o executator is player
        if(!(sender instanceof Player)){
            sender.sendMessage("Só pode ser executado por jogadores");
            return false;
        }

        Player player = (Player) sender;

        Inventory inv = Bukkit.createInventory(player, 45, ChatColor.RED.toString() + ChatColor.BOLD + "Tool menu!");

        //TELEPORT
        ItemStack teleport = new ItemStack(Material.ENDER_PEARL);
        ItemMeta teleportMeta = teleport.getItemMeta();
        teleportMeta.setDisplayName(ChatColor.BLUE + "Teleportar");
        teleportMeta.setLore(Arrays.asList(ChatColor.GRAY + "Teleports to a random player!"));
        teleport.setItemMeta(teleportMeta);

        inv.setItem(20, teleport);


        //KILL YOURSELF

        ItemStack kys = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta kysMeta = kys.getItemMeta();
        kysMeta.setDisplayName(ChatColor.RED + "Kill your self");
        kysMeta.setLore(Arrays.asList(ChatColor.GRAY + "Kill your self!"));
        kys.setItemMeta(kysMeta);

        inv.setItem(22, kys);


        //CLEAR INVENTORY

        ItemStack clearInv = new ItemStack(Material.BUCKET);
        ItemMeta clearInvMeta = clearInv.getItemMeta();
        clearInvMeta.setDisplayName(ChatColor.YELLOW + "Clear Inventory");
        clearInvMeta.setLore(Arrays.asList(ChatColor.GRAY + "Clear Inventory!"));
        clearInv.setItemMeta(clearInvMeta);

        inv.setItem(24, clearInv);

        //CLOSE BUTTON

        ItemStack closeInv = new ItemStack(Material.BARRIER);
        ItemMeta closeInvMeta = closeInv.getItemMeta();
        closeInvMeta.setDisplayName(ChatColor.YELLOW + "Close Inventory");
        closeInvMeta.setLore(Arrays.asList(ChatColor.GRAY + "Close Inventory!"));
        closeInv.setItemMeta(closeInvMeta);

        inv.setItem(0, closeInv);

        //Frames

        ItemStack frame = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        for(int i : new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 17, 18,  26, 27, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44}){
            inv.setItem(i, frame);
        }


        player.openInventory(inv);

        return true;
    }
}
