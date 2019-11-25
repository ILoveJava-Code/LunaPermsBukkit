package de.ilovejava.lunaperms;

import java.util.HashMap;
import java.util.HashSet;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import de.ilovejava.config.Config_Value;
import de.ilovejava.events.Event_Chat;
import de.ilovejava.events.Event_Join;
import de.ilovejava.events.Event_Leave;
import de.ilovejava.groups.Group;
import de.ilovejava.multi.SetUPClient;
import de.ilovejava.permissionbase.LunaPermissibleBase;
import de.ilovejava.utils.Utils;

public class LunaPerms extends JavaPlugin{
	@Override
	public void onEnable() {
		Utils.setInstance(this);
		Utils.setGroups(new HashMap<String, Group>());
		Utils.setUserGroups(new HashMap<String, Group>());
		Utils.setPlayerPermission(new HashMap<>());
		
		new Config_Value();
		new SetUPClient();
		
		HashSet<Listener> events = new HashSet<Listener>();
		
		events.add(new Event_Join());
		events.add(new Event_Leave());
		events.add(new Event_Chat());
		for(Listener l : events) {
			this.getServer().getPluginManager().registerEvents(l, this);
		}
		
		try{reloadInject();}catch(Exception e) {e.printStackTrace();}
	}
	
	@Override
	public void onDisable() {
		
	}
	
	@SuppressWarnings("static-access")
	private void reloadInject() throws Exception {
		for(Player p : Bukkit.getOnlinePlayers()) {
			LunaPermissibleBase cp = new LunaPermissibleBase(p);
			cp.inject(p);
		}
	}
}
