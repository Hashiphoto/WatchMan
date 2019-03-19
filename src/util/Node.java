package util;

/**
 * A Node represents a single end point or ip address to ping
 * @author Trent
 *
 */
public class Node {
	public String ipAddress;
	public String hostName;
	public boolean online;
	
	public Node(String ip, String hostName) {
		this.ipAddress = ip;
		this.hostName = hostName;
		online = true;
	}
}
