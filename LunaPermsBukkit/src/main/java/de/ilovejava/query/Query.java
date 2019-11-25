package de.ilovejava.query;

import java.io.DataOutputStream;
import java.net.Socket;

import de.ilovejava.log.Log;

public class Query extends Log implements Querys{

	public void GetGroups(Socket c) {
		sendQuery("INSERT>ALL>GROUPS", c);
	}

	public void GetPermissionsToGroups(Socket c) {
		sendQuery("INSERT>ALL>PERMISSIONS", c);
	}

	public void GetPrefixToGroups(Socket c) {
		sendQuery("INSERT>ALL>PREFIXS", c);
	}

	public void GetTabToGroups(Socket c) {
		sendQuery("INSERT>ALL>TABLISTS", c);
	}

	public void GetTagIDToGroups(Socket c) {
		sendQuery("INSERT>ALL>TAGIDS", c);
	}

	public void sendQuery(String Query, Socket c) {
		try {
			DataOutputStream out = new DataOutputStream(c.getOutputStream());
			out.writeUTF(Query);
		} catch (Exception e) {e.getStackTrace();}
	}

	public void GetUserToGroups(Socket c) {
		sendQuery("INSERT>ALL>USER", c);
	}

	@Override
	public void GetGroupChatColor(Socket c) {
		sendQuery("INSERT>ALL>CHATCOLOR", c);
	}
	
}
