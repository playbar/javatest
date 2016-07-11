package net.stream;

import java.net.*;
import java.io.*;

public class LowPortScanner {
	public static void main( String[] args ){
		try{
		Socket socket = new Socket("www.baidu.com", 80);
		SocketAddress baidu = socket.getRemoteSocketAddress();
		socket.close();
		}catch(IOException ex ){
			
		}
		String host = "localhost";
		for( int i = 0; i < 1024; ++i ){
			try{
				Socket s = new Socket( host, i );
				System.out.println("There is a server on port " + i + " of " + host);
				s.close();
			}catch( UnknownHostException ex ){
				System.out.println(ex);
				break;
			}catch( IOException ex){
				
			}
		}
	}
}
