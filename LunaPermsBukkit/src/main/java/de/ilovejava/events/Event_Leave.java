package de.ilovejava.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;

public class Event_Leave implements Listener {
	@EventHandler
	public void leav(PlayerToggleSneakEvent e) {
		if(e.isSneaking()) {
			if(e.getPlayer().hasPermission("Test.Test")) {
				System.out.println("Hallo :D");
			}
		}
	}
}
