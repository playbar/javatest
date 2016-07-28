package net.ssl;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.net.*;
import java.io.IOException;

public class ChargenClient {
	public static int DEFAULT_PORT = 19;
	public static void main( String[] args ){
		int port = DEFAULT_PORT;
		try{
			SocketAddress address = new InetSocketAddress("http://rama.polyy.edu", port );
			SocketChannel client = SocketChannel.open( address );
			ByteBuffer buffer = ByteBuffer.allocate( 74 );
			WritableByteChannel out = Channels.newChannel(System.out);
			
			while( client.read(buffer) != -1 ){
				buffer.flip();
				out.write(buffer);
				buffer.clear();
			}
		}catch( IOException | UnresolvedAddressException ex ){
			ex.printStackTrace();
		}
	}
}
