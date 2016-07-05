package net.stream;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class IPCharacteristics {
	public static void main(String[] args ){
		try{
			InetAddress address = InetAddress.getByName("www.baidu.com");
			if( address.isAnyLocalAddress() ){
				System.out.println(address + " is a wildcard address.");
			}
			if( address.isLoopbackAddress() ){
				
			}
		}catch(UnknownHostException ex ){
			
		}
	}
}
