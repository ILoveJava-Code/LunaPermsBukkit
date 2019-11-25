package de.ilovejava.permissionbase;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissibleBase;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.permissions.ServerOperator;

public class LunaPermissibleBase extends PermissibleBase{

	public LunaPermissibleBase(ServerOperator opable) {
		super(opable);
	}

	 	@Override
	    public boolean hasPermission(Permission perm) {
	    	return hasPermission(perm.getName());
	    }
	    
	   @Override 
	   public boolean hasPermission(String inName) {
		   
		   if(super.hasPermission("-"+inName)) {
			   return false;
		   }
		   
		   if (super.isOp() && (!hasPerm("-" + inName))) {
	           return true;
	       }
	       if(super.hasPermission("*") || super.hasPermission("'*'")) {
	            return true;
	       }
	       return super.hasPermission(inName);
	   }
	   
	@SuppressWarnings("unchecked")
	private boolean hasPerm(String inName) {
	 
		 if (inName == null) {
		 throw new IllegalArgumentException("Permission name cannot be null");
		 }

		 Map<String, PermissionAttachmentInfo> permissions = new HashMap<String, PermissionAttachmentInfo>();
		         try {
		         try {
		             Field f = PermissibleBase.class.getDeclaredField("permissions");
		             f.setAccessible(true);
		             permissions = (Map<String, PermissionAttachmentInfo>) f.get(this);
		             f.setAccessible(false);
		         } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
		             e.printStackTrace();
		         }


		 String name = inName.toLowerCase();

		 if (isPermissionSet(name)) {
		 return permissions.get(name).getValue();
		 } else {
		 Permission perm = Bukkit.getServer().getPluginManager().getPermission(name);

		                 if (perm != null) {
		                 return perm.getDefault().getValue(false);
		             } else {
		                 return Permission.DEFAULT_PERMISSION.getValue(false);
		             }
		     }
		 }catch(Exception e) {e.printStackTrace();}
				return false;
	 }
	   
	 public static void inject(Player p) throws Exception {
	    String version = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];
	    Field field = Class.forName("org.bukkit.craftbukkit." + version + ".entity.CraftHumanEntity").getDeclaredField("perm");  
	    field.setAccessible(true); 
	    Field modifiersField = field.getClass().getDeclaredField("modifiers");  
	    modifiersField.setAccessible(true);   
	    modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
	    field.set(p, new LunaPermissibleBase(p));
	  }
}
