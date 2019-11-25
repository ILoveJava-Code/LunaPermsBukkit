package de.ilovejava.client;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

import org.bukkit.Bukkit;

import de.ilovejava.groups.Group;
import de.ilovejava.query.Query;
import de.ilovejava.utils.Utils;
import net.md_5.bungee.api.ChatColor;

public class Client extends Query{
	Socket c;
	public Client(Socket c) {
		this.c = c;
		this.getGroups();
	}
	
	private void getGroups() {
		this.GetGroups(c);
		this.waitforquery();
	}
	
	private void waitforquery() {
		Timer t = new Timer();
		t.schedule(new TimerTask() {
			
			@Override
			public void run() {
				try {
					String[] query = getQuery(c).split(">");
					if(query[0].equalsIgnoreCase("INSERT")) {
						if(query[1].equalsIgnoreCase("GROUP")) {
							new Group(query[2]);
						}
					}else if(query[0].equalsIgnoreCase("FUNCTION")) {
						if(query[1].equalsIgnoreCase("GROUPSEND")) {
							if(query[2].equalsIgnoreCase("END")) {
								GetPermissionsToGroups(c);
							}
						}else if(query[1].equalsIgnoreCase("PERMISSIONSEND")) {
							if(query[2].equalsIgnoreCase("END")) {
								GetPrefixToGroups(c);
							}
						}else if(query[1].equalsIgnoreCase("PREFIXSEND")) {
							if(query[2].equalsIgnoreCase("END")) {
								GetTabToGroups(c);
							}
						}else if(query[1].equalsIgnoreCase("TABSEND")) {
							if(query[2].equalsIgnoreCase("END")) {
								GetTagIDToGroups(c);
							}
						}else if(query[1].equalsIgnoreCase("TAGSEND")) {
							if(query[2].equalsIgnoreCase("END")) {
								GetUserToGroups(c);
							}
						}else if(query[1].equalsIgnoreCase("USERSEND")) {
							if(query[2].equalsIgnoreCase("END")) {
								GetGroupChatColor(c);
							}
						}else if(query[1].equalsIgnoreCase("COLORSEND")) {
							if(query[2].equalsIgnoreCase("END")) {
								SetUpHasFin();
							}
						}else if(query[1].equalsIgnoreCase("UPDATE")) {
							if(query[2].equalsIgnoreCase("PERMISSION")) {
								if(query[3].equalsIgnoreCase("ADD")) {
									String group = query[4];
									String permission = query[5];
									Utils.getGroups().get(group).addPermission(permission);
								}else if(query[3].equalsIgnoreCase("REMOVE")) {
									String group = query[4];
									String permission = query[5];
									Utils.getGroups().get(group).removePermission(permission);
								}
							}
						}
					}else if(query[0].equalsIgnoreCase("UPDATE")) {
						if(query[1].equalsIgnoreCase("GROUP")) {
							if(query[2].equalsIgnoreCase("PERMISSION")) {
								if(query.length == 4) { GroupHasNotPerms(query[3]); return;}
								Utils.getGroups().get(query[3]).InsertPermission(query[4]);
							}
						}else if(query[1].equalsIgnoreCase("USER")) {
							if(query[2].equalsIgnoreCase("REMOVE")) {
								if(query[3].equalsIgnoreCase("GROUP")) {
									String uuid = query[4];
									String group = query[5];
									Utils.getGroups().get(group).removePlayer(uuid);
								}
							}else if(query[2].equalsIgnoreCase("ADD")) {
								if(query[3].equalsIgnoreCase("GROUP")) {
									String uuid = query[4];
									String group = query[5];
									Utils.getGroups().get(group).addPlayer(uuid);
								}
							}
						}
					}else if(query[0].equalsIgnoreCase("STRING")) {
						if(query[1].equalsIgnoreCase("UPDATE")) {
							if(query[2].equalsIgnoreCase("GROUP")) {
								if(query[3].equalsIgnoreCase("PREFIX")) {
									Utils.getGroups().get(query[4]).setPrefix(ChatColor.translateAlternateColorCodes('&', query[5]));
								}else if(query[3].equalsIgnoreCase("TABLIST")) {
									Utils.getGroups().get(query[4]).setTabList(ChatColor.translateAlternateColorCodes('&', query[5]));
								}else if(query[3].equalsIgnoreCase("TAGID")) {
									Utils.getGroups().get(query[4]).setTagID(Integer.valueOf(query[5]));
								}else if(query[3].equalsIgnoreCase("USER")) {
									if(query.length == 5) { GroupHasNoUser(query[4]); return;}
									Utils.getGroups().get(query[4]).insertUser(query[5]);
								}else if(query[3].equalsIgnoreCase("COLOR")) {
									Utils.getGroups().get(query[4]).setChatColor(query[5]);
								}
							}
						}
					}
				}catch(Exception e) {QueryError("Connection Close", this.getClass().getSimpleName()); this.cancel(); Bukkit.shutdown();}
			}
		}, 0, 100);
	}
	
	public String getQuery(Socket c) throws IOException {
		String qry = null;
		DataInputStream in = new DataInputStream(c.getInputStream());
		qry = in.readUTF();
		return qry;
	}
	
}
