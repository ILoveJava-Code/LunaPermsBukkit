package de.ilovejava.utils;

import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

import de.ilovejava.groups.Group;
import de.ilovejava.lunaperms.LunaPerms;
import lombok.Getter;
import lombok.Setter;

public class Utils {
	@Getter @Setter
	public static LunaPerms instance;
	@Getter @Setter
	public static HashMap<String, Group>Groups;
	@Getter @Setter
	public static HashMap<String, Group> UserGroups;
	@Getter @Setter
	public static HashMap<Player, PermissionAttachment> PlayerPermission;
}
