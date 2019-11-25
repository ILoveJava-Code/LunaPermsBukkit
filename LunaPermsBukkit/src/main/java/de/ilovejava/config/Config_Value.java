package de.ilovejava.config;

import de.ilovejava.log.Log;
import de.ilovejava.utils.Utils;

public class Config_Value extends Log implements Config{
	
	private String ip;
	private Integer port;
	private String user;
	private String Password;
	private String format;
	
	public Config_Value() {this.update();}

	public String MulitHost() {
		return this.ip;
	}

	public Integer MulitPort() {
		return this.port;
	}

	public String MulitUser() {
		return this.user;
	}

	public String MutilPassword() {
		return this.Password;
	}

	public void update() {
		isSet("Config.Host", "Host");
		isSet("Config.User", "User");
		isSet("Config.Password", "Password");
		isSet("Config.Port", 1337);
		isSet("Config.Format", "%{Rang}% %{Player}%:%{ChatColor}% %{Message}%");
		
		this.setHost(Utils.getInstance().getConfig().getString("Config.Host"));
		this.setUser(Utils.getInstance().getConfig().getString("Config.User"));
		this.setPassword(Utils.getInstance().getConfig().getString("Config.Password"));
		this.setPort(Utils.getInstance().getConfig().getInt("Config.Port"));
		this.setFormat(Utils.getInstance().getConfig().getString("Config.Format"));
	}

	public void setHost(String s) {
		this.ip = s;
	}

	public void setPort(Integer i) {
		this.port = i;
	}

	public void setUser(String s) {
		this.user = s;
	}

	public void setPassword(String s) {
		this.Password = s;
	}

	public void isSet(String key, Object obj) {
		if(!Utils.getInstance().getConfig().isSet(key)) {
			Utils.getInstance().getConfig().set(key, obj);
			Utils.getInstance().saveConfig();
		}
	}

	@Override
	public String ChatFormat() {
		return this.format;
	}

	@Override
	public void setFormat(String format) {
		this.format = format;
	}
}
