package de.ilovejava.groups;

import java.util.ArrayList;

public interface Groups {
	public String Name();
	public ArrayList<String> Permissions();
	public ArrayList<String> PermissionUser();
	public String chatColor();
	
	public void setChatColor(String color);
	public void setName(String name);
	public void addPermission(String s);
	public void removePermission(String s);
	
	public void delete();
	
	public void addPlayer(String uuid);
	public void removePlayer(String uuid);
}
