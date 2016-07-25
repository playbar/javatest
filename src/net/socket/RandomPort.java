package net.socket;

import java.io.*;
import java.net.*;

public class RandomPort {
	
	public static void main( String []args ){
		try{
			ServerSocket server = new ServerSocket( 0);
			System.out.println("This server runs on port " + server.toString() );
		}catch( IOException ex ){
			System.out.println(ex.getMessage());
		}
	}
}
