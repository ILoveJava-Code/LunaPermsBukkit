package de.ilovejava.config;

public interface Config {
	public String MulitHost();
	public Integer MulitPort();
	public String MulitUser();
	public String MutilPassword();
	public String ChatFormat();
	
	public void update();
	
	public void setHost(String s);
	public void setPort(Integer i);
	public void setUser(String s);
	public void setPassword(String s);
	public void isSet(String key, Object obj);
	public void setFormat(String format);
}
