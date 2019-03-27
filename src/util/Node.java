package util;

/**
 * A Node represents a single end point or ip address to ping
 * @author Trent
 *
 */
public class Node {
	public String ipAddress;
	public String hostName;
	public String state;
	
	public Node(String ip, String hostName, String state) {
		this.ipAddress = ip;
		this.hostName = hostName;
		this.state = state;
	}
}
