package net.ssl;

import java.io.*;
import java.nio.*;
import java.nio.channels.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.util.*;
import java.net.*;

public class NonblockingSingleFiledHTTPServer {
	private ByteBuffer contentBuffer;
	private int port = 80;
	public NonblockingSingleFiledHTTPServer(ByteBuffer data,String encoding, 
			String MIMEType, int port){
		this.port = port;
		String header = "HTTP/1.0 200 ok\r\n"
				+ "Server: NonblockingSingleFiledHTTPServer\r\n"
				+ "Content-length: " + data.limit() + "\r\n"
				+ "Content-type: " + MIMEType + "\r\n\r\n";
		byte[] headerData = header.getBytes(Charset.forName("US-ASCII"));
		
		ByteBuffer buffer = ByteBuffer.allocate(data.limit() + headerData.length );
		buffer.put(data);
		buffer.flip();
		this.contentBuffer = buffer;
	}
	public void run() throws IOException{
		ServerSocketChannel serverChannel = ServerSocketChannel.open();
		ServerSocket serverSocket = serverChannel.socket();
		Selector selector = Selector.open();
		InetSocketAddress localPort = new InetSocketAddress(port);
		serverSocket.bind(localPort);
		serverChannel.configureBlocking(false);
		serverChannel.register(selector, SelectionKey.OP_ACCEPT);
		
		while( true ){
			selector.select();
			Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
			while( keys.hasNext()){
				SelectionKey key = keys.next();
				keys.remove();
				try{
					if( key.isAcceptable()){
						ServerSocketChannel server = (ServerSocketChannel) key.channel();
						SocketChannel channel = server.accept();
						channel.configureBlocking(false);
						channel.register(selector, SelectionKey.OP_READ );
					}else if( key.isWritable()){
						SocketChannel channel = (SocketChannel) key.channel();
						ByteBuffer buffer = (ByteBuffer)key.attachment();
						if( buffer.hasRemaining() ){
							channel.write(buffer );
						}else{
							channel.close();
						}
					}else if( key.isReadable() ){
						SocketChannel channel = (SocketChannel)key.channel();
						ByteBuffer buffer = ByteBuffer.allocate(4096);
						channel.read(buffer );
						key.interestOps(SelectionKey.OP_WRITE);
						key.attach(contentBuffer.duplicate());
					}
				}catch( IOException ex ){
					key.cancel();
					try{
						key.channel().close();
					}catch( IOException cex){}
				}
			}
		}
	}
	
	public static void main( String []args ){
		String strPath = "http://www.baidu.com";
		try{
			String contentType = URLConnection.getFileNameMap().getContentTypeFor(strPath);
			Path file = FileSystems.getDefault().getPath(strPath);
			byte []data = Files.readAllBytes(file);
			ByteBuffer input = ByteBuffer.wrap(data);
			int port = 80;
			String encoding = "UTF-8";
			NonblockingSingleFiledHTTPServer server = 
					new NonblockingSingleFiledHTTPServer( input, encoding, contentType, port);
			server.run();
		}catch( IOException ex ){
			System.out.println(ex);
		}
	}
}
