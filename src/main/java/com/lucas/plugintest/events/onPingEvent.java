package com.lucas.plugintest.events;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class onPingEvent implements Listener {

    @EventHandler
    public void onPing(ServerListPingEvent pingEvent){
        pingEvent.setMaxPlayers(25);
        pingEvent.setMotd(ChatColor.RED + "Luquinhas Test Server\n" + "just testing some plugins");
    }



}
