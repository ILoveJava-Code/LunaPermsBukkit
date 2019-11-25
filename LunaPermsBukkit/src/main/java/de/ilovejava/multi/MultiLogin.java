package de.ilovejava.multi;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import org.bukkit.Bukkit;

import de.ilovejava.client.Client;
import de.ilovejava.log.Log;
import de.ilovejava.utils.Utils;

public class MultiLogin extends Thread{
	String ip;
	String user;
	String password;
	Integer port;
	
	public MultiLogin(String IP, String User, String Password, Integer Port) {
		this.ip = IP;
		this.user = User;
		this.password = Password;
		this.port = Port;
	}
	
	@SuppressWarnings("static-access")
	@Override
	public void run() {
		Log l = new Log();
		try {
			Socket c = new Socket(ip, port);
			sendQuery(c, "LOGIN>"+user+">"+password);
			String qry = getQuery(c);
			sendConnectionResult(c,qry);
			this.sleep(100);
		}catch(Exception e) {l.ConnectionError(); Bukkit.getPluginManager().disablePlugin(Utils.getInstance());}
	}
	
	private void sendConnectionResult(Socket c, String qry) {
		String[] ar = qry.split(">");
		Log l = new Log();
		if(ar[0].equalsIgnoreCase("FUNCTION")) {
			if(ar[1].equalsIgnoreCase("USER")) {
				if(ar[2].equalsIgnoreCase("DENY")) {
					l.UserDeny();
				}
			}else if(ar[1].equalsIgnoreCase("PASSWORD")) {
				if(ar[2].equalsIgnoreCase("DENY")) {
					l.PasswordDeny();
				}
			}else if(ar[1].equalsIgnoreCase("LOGIN")) {
				if(ar[2].equalsIgnoreCase("ACCEPT")) {
					l.ConnectionReady(ip);
					new Client(c);
				}
			}
		}
	}
	
	public void sendQuery(Socket c, String Query) {
		try {
			DataOutputStream out = new DataOutputStream(c.getOutputStream());
			out.writeUTF(Query);
		} catch (Exception e) {e.getStackTrace();}
	}
	
	public String getQuery(Socket c) throws IOException {
		String qry = null;
		DataInputStream in = new DataInputStream(c.getInputStream());
		qry = in.readUTF();
		return qry;
	}
	
}
