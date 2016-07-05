package net.stream;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class MyAddress {
	public static void main(String[] args ){
		try{
			InetAddress me = InetAddress.getLocalHost();
			String dottedOuad = me.getHostAddress();
			System.out.println("My address is " + dottedOuad );
		}catch(UnknownHostException ex ){
			System.out.println("I'm sorry, I don't know my own address.");
		}
	}
}
