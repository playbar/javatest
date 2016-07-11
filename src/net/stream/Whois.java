package net.stream;

import java.net.*;
import java.io.*;

public class Whois {
	
	public final static int DEFAULT_PORT = 43;
	public final static String DEFAULT_HOST = "whois.internic.net";
	
	private int port = DEFAULT_PORT;
	private InetAddress host;
	
	public Whois( InetAddress host, int port ){
		this.port = port;
		this.host = host;
	}
	
}
