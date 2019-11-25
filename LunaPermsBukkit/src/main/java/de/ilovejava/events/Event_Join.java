package de.ilovejava.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.permissions.PermissionAttachment;

import de.ilovejava.groups.Group;
import de.ilovejava.permissionbase.LunaPermissibleBase;
import de.ilovejava.utils.Utils;
import de.ilovejava.uuid.uuidfetcher;

public class Event_Join implements Listener {
	@SuppressWarnings("static-access")
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		String uuid = uuidfetcher.getUUID(e.getPlayer().getName()).toString();
		if(!Utils.getUserGroups().containsKey(uuid)) {
			Utils.getUserGroups().put(uuid, Utils.getGroups().get("default"));
		}
		
		try {
			LunaPermissibleBase cp = new LunaPermissibleBase(e.getPlayer());
			cp.inject(e.getPlayer());
		}catch(Exception e1) {e1.printStackTrace();}
		setPermission(e.getPlayer(), Utils.getUserGroups().get(uuid));
	}
	
	private void setPermission(Player p, Group g) {
		PermissionAttachment at = p.addAttachment(Utils.getInstance());
		for(String perm : g.Permissions()) {
			at.setPermission(perm, true);
		}
	}
}
