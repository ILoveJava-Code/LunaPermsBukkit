package de.ilovejava.log;

public interface Logs {
	public void StartigConnection();
	public void ConnectionError();
	public void QueryError(String name, String Class);
	public void ConnectionReady(String name);
	public void PasswordDeny();
	public void UserDeny();
	public void loadUser(String name);
	public void PermissionLoadedForGroup(Integer i, String name);
	public void GroupHasNotPerms(String name);
	public void SetPrefixForGroup(String name);
	public void SetTagIDForGroup(String name);
	public void SetTabListForGroup(String name);
	public void SetUserForGroup(Integer i, String p);
	public void GroupHasNoPrefix(String group);
	public void GroupHasNoTabList(String group);
	public void GroupHasNoUser(String group);
	public void SetUpHasFin();
}
