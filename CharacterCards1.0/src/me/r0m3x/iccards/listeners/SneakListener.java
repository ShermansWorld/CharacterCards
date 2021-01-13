package me.r0m3x.iccards.listeners;

import java.util.concurrent.TimeUnit;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;

import me.r0m3x.iccards.API;

public class SneakListener implements Listener {
	@EventHandler
	public void PlayerToggleSneakEvent(PlayerToggleSneakEvent e) throws InterruptedException {

		Player p = e.getPlayer();
		if (API.getProfession(p).equalsIgnoreCase("Thief")) {
			if (e.isSneaking()) {
				TimeUnit.MILLISECONDS.sleep(6);
				p.setWalkSpeed(0.25F);
				TimeUnit.MILLISECONDS.sleep(6);
				p.setWalkSpeed(0.3F);
				TimeUnit.MILLISECONDS.sleep(6);
				p.setWalkSpeed(0.35F);
				TimeUnit.MILLISECONDS.sleep(6);
				p.setWalkSpeed(0.4F);
				TimeUnit.MILLISECONDS.sleep(6);
				p.setWalkSpeed(0.45F);
				TimeUnit.MILLISECONDS.sleep(6);
				p.setWalkSpeed(0.5F);
				TimeUnit.MILLISECONDS.sleep(6);
				p.setWalkSpeed(0.55F);
				TimeUnit.MILLISECONDS.sleep(6);
				p.setWalkSpeed(0.6F);
			} else {
				TimeUnit.MILLISECONDS.sleep(9);
				p.setWalkSpeed(0.5F);
				TimeUnit.MILLISECONDS.sleep(9);
				p.setWalkSpeed(0.45F);
				TimeUnit.MILLISECONDS.sleep(9);
				p.setWalkSpeed(0.4F);
				TimeUnit.MILLISECONDS.sleep(9);
				p.setWalkSpeed(0.35F);
				TimeUnit.MILLISECONDS.sleep(9);
				p.setWalkSpeed(0.3F);
				TimeUnit.MILLISECONDS.sleep(9);
				p.setWalkSpeed(0.25F);
				TimeUnit.MILLISECONDS.sleep(9);
				p.setWalkSpeed(0.2F);
			}
		}
	}
}
