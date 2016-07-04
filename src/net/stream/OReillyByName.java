package net.stream;

import java.net.*;

public class OReillyByName {
	public static void main( String [] args ){
		try{
			InetAddress address = InetAddress.getLocalHost();
			System.out.println(address);
			
		}catch( UnknownHostException ex ){
			System.out.println("Could not find www.oreilly.com");
		}
	}
}
