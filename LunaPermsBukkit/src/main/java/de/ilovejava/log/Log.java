package de.ilovejava.log;

import org.bukkit.Bukkit;

public class Log implements Logs{

	public void StartigConnection() {
		Bukkit.getConsoleSender().sendMessage("§bThe connection to the head client is created!");
	}

	public void ConnectionError() {
		Bukkit.getConsoleSender().sendMessage("§cThe connection to the head client could not be created please check the data and check the data for correctness");
	}

	public void QueryError(String name, String Class) {
		Bukkit.getConsoleSender().sendMessage("§cAttention, there is an error in the query system, look in class:§e "+Class+"§c from the user:§e "+name);
	}

	public void ConnectionReady(String name) {
		Bukkit.getConsoleSender().sendMessage("§bThe connection to the host has been created! IP: §e" + name);
	}

	public void PasswordDeny() {
		Bukkit.getConsoleSender().sendMessage("§cAttention, specify the password for the head client is wrong or no longer active!");
	}

	public void UserDeny() {
		Bukkit.getConsoleSender().sendMessage("§cAttention, the specified user was not found or is inactive");
	}

	public void loadUser(String name) {
		Bukkit.getConsoleSender().sendMessage("§bThe group: §e"+name+"§b was successfully loaded!");
	}

	public void PermissionLoadedForGroup(Integer i, String name) {
		Bukkit.getConsoleSender().sendMessage("§e"+i+"§b Permissions for the group:§e "+name+"§b loaded!");
	}

	public void GroupHasNotPerms(String name) {
		Bukkit.getConsoleSender().sendMessage("§cThe group: §e"+name+"§c has no permission!");
	}

	public void SetPrefixForGroup(String name) {
		Bukkit.getConsoleSender().sendMessage("§bThe prefix for the group: §e"+name+" §bhas been set!");
	}

	public void SetTagIDForGroup(String name) {
		Bukkit.getConsoleSender().sendMessage("§bThe TagID for the group: §e"+name+" §bhas been set!");
	}

	public void SetTabListForGroup(String name) {
		Bukkit.getConsoleSender().sendMessage("§bThe TabList string for the group: §e"+name+" §bhas been set!");
	}

	public void SetUserForGroup(Integer i, String p) {
		Bukkit.getConsoleSender().sendMessage("§e" + i + " §b Players were placed in the group: §e" + p);
	}

	public void GroupHasNoPrefix(String group) {
		Bukkit.getConsoleSender().sendMessage("§cAttention, the group:§e "+group+"§c has not got a prefix yet");
	}

	public void GroupHasNoTabList(String group) {
		Bukkit.getConsoleSender().sendMessage("§cAttention, you group: §e"+group+"§c did not get a tablet sign!");
	}

	public void GroupHasNoUser(String group) {
		Bukkit.getConsoleSender().sendMessage("§cAttention, the group: §e"+group+"§c has not registered any players!");
	}

	@Override
	public void SetUpHasFin() {
		Bukkit.getConsoleSender().sendMessage("§bThe system has successfully loaded the groups the system is now ready!");
	}

}
