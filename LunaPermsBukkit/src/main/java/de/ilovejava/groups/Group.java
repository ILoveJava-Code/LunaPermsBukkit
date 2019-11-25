package de.ilovejava.groups;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

import de.ilovejava.log.Log;
import de.ilovejava.utils.Utils;
import de.ilovejava.uuid.uuidfetcher;

public class Group extends Log implements Groups{

	private String name;
	private String prefix;
	private String TabList;
	private Integer TagID;
	private String chatcolor;
	private ArrayList<String> permission;
	private ArrayList<String> user;
	
	public Group(String name) {
		this.setName(name);
		this.loadUser(name);
		Utils.getGroups().put(name, this);
		this.user = new ArrayList<String>();
		this.permission = new ArrayList<String>();
	}
	
	public void setPrefix(String s) {
		this.SetPrefixForGroup(name);
		if(s.equals("null")) {this.GroupHasNoPrefix(name);}
		this.prefix = s;
	}
	
	public void setTabList(String s) {
		if(s.equals("null")) {this.GroupHasNoTabList(name);}
		this.SetTabListForGroup(name);
		this.TabList = s;
	}
	
	public void setTagID(Integer i) {
		this.SetTagIDForGroup(name);
		this.TagID = i;
	}
	
	public String getPrefix() {
		return this.prefix;
	}
	
	public String getTabList() {
		return this.TabList;
	}
	
	public Integer getTagID() {
		return this.TagID;
	}
	
	public String Name() {
		return this.name;
	}

	public ArrayList<String> Permissions() {
		return this.permission;
	}

	public ArrayList<String> PermissionUser() {
		return this.user;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addPermission(String s) {
		this.permission.add(s);
		this.addNewPlayerPermission(s);
	}

	public void removePermission(String s) {
		this.permission.remove(s);
		this.removePlayerPermission(s);
	}

	public void delete() {
		this.permission.clear();
		this.user.clear();
		Utils.getGroups().remove(this.name);
	}

	public void insertUser(String user) {
		int i = 0;
		String[] au = user.split(",");
		for(String us : au) {
			this.addPlayer(us);
			Utils.getUserGroups().put(us, this);
			i++;
		}
		this.SetUserForGroup(i, name);
	}
	
	public void InsertPermission(String perm) {
		int i = 0;
		String[] perms = perm.split(",");
		for(String permission : perms) {
			this.addPermission(permission);
			i++;
		}
		this.PermissionLoadedForGroup(i, name);
	}
	
	@SuppressWarnings("deprecation")
	public void addNewPlayerPermission(String Permission) {
		for(String s : this.user) {
			if(Bukkit.getOfflinePlayer(uuidfetcher.getName(UUID.fromString(s))).isOnline()) {
				Player p = Bukkit.getPlayer(uuidfetcher.getName(UUID.fromString(s)));
				PermissionAttachment pa = p.addAttachment(Utils.getInstance());
				pa.setPermission(Permission, true);
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	public void removePlayerPermission(String Permission) {
		for(String s : this.user) {
			System.out.println("Remove Permission!");
			if(Bukkit.getOfflinePlayer(uuidfetcher.getName(UUID.fromString(s))).isOnline()) {
				Player p = Bukkit.getPlayer(uuidfetcher.getName(UUID.fromString(s)));
				PermissionAttachment pa = p.addAttachment(Utils.getInstance());
				pa.setPermission(Permission, false);
			}
		}
	}
	
	public void addPlayer(String uuid) {
		this.user.add(uuid);
		addPlayerPermissions(uuid);
		Utils.getUserGroups().put(uuid, this);
	}

	@SuppressWarnings("deprecation")
	private void addPlayerPermissions(String uuid) {
		if(Bukkit.getOfflinePlayer(uuidfetcher.getName(UUID.fromString(uuid))).isOnline()) {
			PermissionAttachment pa = Bukkit.getPlayer(uuidfetcher.getName(UUID.fromString(uuid))).addAttachment(Utils.getInstance());
			for(String perm : this.permission) {
				pa.setPermission(perm, true);
			}
		}
	}
	
	public void removePlayer(String uuid) {
		this.user.remove(uuid);
		removePlayerPermissions(uuid);
		Utils.getGroups().get("default").addPlayer(uuid);
	}

	@SuppressWarnings("deprecation")
	private void removePlayerPermissions(String uuid) {
		if(Bukkit.getOfflinePlayer(uuidfetcher.getName(UUID.fromString(uuid))).isOnline()) {
			PermissionAttachment pa = Bukkit.getPlayer(uuidfetcher.getName(UUID.fromString(uuid))).addAttachment(Utils.getInstance());
			for(String perm : this.permission) {
				pa.setPermission(perm, false);
			}
		}
	}
	
	@Override
	public String chatColor() {
		return this.chatcolor;
	}

	@Override
	public void setChatColor(String color) {
		this.chatcolor = color;
	}

}
