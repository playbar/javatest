package net.server;

import java.io.*;
import java.net.*;
import java.util.Date;

public class TimeServer {
	public final static int PORT = 37;
	public static void main( String[] args ){
		long differenceBetweenEpochs = 2208988800L;
		
		try( ServerSocket server = new ServerSocket(PORT)){
			while( true ){
				try( Socket connection = server.accept()){
					OutputStream out = connection.getOutputStream();
					Date now = new Date();
					long msSince1970 = now.getTime();
					long secondSince1970 = msSince1970 / 1000;
					long secondSince1990 = secondSince1970 + differenceBetweenEpochs;
					
					byte[] time = new byte[4];
					time[0] = (byte)((secondSince1990 & 0x00000000FF000000L) >> 24);
					time[1] = (byte)((secondSince1990 & 0x0000000000FF0000L) >> 16);
					time[2] = (byte)((secondSince1990 & 0x000000000000FF00L) >> 8);
					time[3] = (byte)((secondSince1990 & 0x00000000000000FFL));
					out.write(time);
					out.flush();
				}catch( IOException ex ){
					System.err.println( ex.getMessage() );
				}
			}
		}catch(IOException ex ){
			System.out.println( ex );
		}
	}
}
