package me.r0m3x.iccards.listeners;

import java.util.concurrent.TimeUnit;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;

import me.r0m3x.iccards.API;

public class sneakListener implements Listener {
	@EventHandler
	public void PlayerToggleSneakEvent(PlayerToggleSneakEvent e) {
		
		Player p = e.getPlayer();
		if(API.getProfession(p).equalsIgnoreCase("Thief")) {
			if (e.isSneaking()) {
		        p.setWalkSpeed(0.6F);
		    }else {
		    	p.setWalkSpeed(0.2F);
		    }
		}
	}
}
