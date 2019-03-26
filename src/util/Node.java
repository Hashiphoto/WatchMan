package util;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * A Node represents a single end point or ip address to ping
 * @author Trent
 *
 */
public class Node {
	public String ipAddress;
	public String hostName;
	public String state;
	public boolean online;
	
	private static final int TIMEOUT_MS = 1;
	
	public Node(String ip, String hostName, String state) {
		this.ipAddress = ip;
		this.hostName = hostName;
		this.state = state;
		online = false;
	}

	/**
	 * Try connection to the node and update online property accordingly
	 */
	public void checkConnection() {
		online = Math.random() < 0.99;
//		InetAddress host;
//		try {
//			host = InetAddress.getByName(ipAddress);
//			online = host.isReachable(TIMEOUT_MS);
//		} catch (UnknownHostException e) {
//			System.out.println("Cannot find " + ipAddress);
////			e.printStackTrace();
//		} catch (IOException e) {
//			System.out.println("IOException " + ipAddress);
////			e.printStackTrace();
//		} finally {
//			System.out.println("online: " + online);
//		}
	}
}
