package net.ssl;

import java.nio.*;
import java.nio.channels.*;
import java.net.*;
import java.util.*;
import java.io.*;

public class ChargenServer {
	public static int DEFAULT_PORT = 19;
	public static void main(String [] args ){
		int port=DEFAULT_PORT;
		
		byte[] rotation = new byte[95*2];
		for( byte i = ' '; i <='~'; ++i ){
			rotation[i-' '] = i;
			rotation[i+95 - ' '] = i;
		}
		ServerSocketChannel serverChannel;
		Selector selector;
		try{
			serverChannel = ServerSocketChannel.open();
			ServerSocket ss = serverChannel.socket();
			InetSocketAddress address = new InetSocketAddress( port );
			ss.bind(address);
			serverChannel.configureBlocking(false);
			selector = Selector.open();
			serverChannel.register(selector, SelectionKey.OP_ACCEPT);
			
		}catch(IOException ex ){
			ex.printStackTrace();
			return;
		}
		
		while( true){
			try{
				selector.select();
			}catch(IOException ex){
				ex.printStackTrace();
				break;
			}
			Set<SelectionKey> readKeys = selector.selectedKeys();
			Iterator<SelectionKey> iterator = readKeys.iterator();
			while( iterator.hasNext() ){
				SelectionKey key = iterator.next();
				iterator.remove();
				try{
					if( key.isAcceptable() ){
						ServerSocketChannel server = (ServerSocketChannel) key.channel();
						SocketChannel client = server.accept();
						System.out.println("Accepted connection from " + client );
						client.configureBlocking(false);
						SelectionKey key2 = client.register(selector, SelectionKey.OP_WRITE );
						ByteBuffer buffer = ByteBuffer.allocate(74);
						buffer.put(rotation, 0, 72);
						buffer.put((byte)'\r');
						buffer.put((byte)'\n');
						buffer.flip();
						key2.attach(key.isWritable());
					}else if( key.isWritable() ){
						SocketChannel client = (SocketChannel)key.channel();
						ByteBuffer buffer = (ByteBuffer)key.attachment();
						if( !buffer.hasRemaining() ){
							buffer.rewind();
							int first = buffer.get();
							buffer.rewind();
							int position = first - ' ' + 1;
							buffer.put(rotation, position, 72 );
							buffer.put((byte)'\r');
							buffer.put((byte)'\n');
							buffer.flip();
						}
						client.write(buffer);
					}
				}catch(IOException ex ){
					key.cancel();
					try{
						key.channel().close();
					}catch(IOException cex ){}
				}
			}
		}
		
	}
}
