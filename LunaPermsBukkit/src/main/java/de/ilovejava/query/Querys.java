package de.ilovejava.query;

import java.net.Socket;

public interface Querys {
	public void GetGroups(Socket c);
	public void GetPermissionsToGroups(Socket c);
	public void GetPrefixToGroups(Socket c);
	public void GetTabToGroups(Socket c);
	public void GetTagIDToGroups(Socket c);
	public void GetUserToGroups(Socket c);
	public void GetGroupChatColor(Socket c);
}
