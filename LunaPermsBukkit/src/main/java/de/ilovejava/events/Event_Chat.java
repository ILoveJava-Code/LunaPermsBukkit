package de.ilovejava.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import de.ilovejava.config.Config_Value;
import de.ilovejava.groups.Group;
import de.ilovejava.utils.Utils;
import de.ilovejava.uuid.uuidfetcher;
import net.md_5.bungee.api.ChatColor;

public class Event_Chat extends Config_Value implements Listener {
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		String uuid = uuidfetcher.getUUID(e.getPlayer().getName()).toString();
		Group g = Utils.getUserGroups().get(uuid);
		String message = this.ChatFormat().replace("%{Rang}%", g.getPrefix()).replace("%{Player}%", e.getPlayer().getDisplayName()).replace("%{ChatColor}%", g.chatColor()).replace("%{Message}%", e.getMessage());
		e.setFormat(ChatColor.translateAlternateColorCodes('&', message));
	}
}
