package net.stream;

import java.io.*;
import java.net.*;

public class SourceViewer {
	public static void main( String[] args ){
		String strurl = "http://www.baidu.com";

			InputStream in = null;
			try{
				URL u = new URL( strurl);
				in = u.openStream();
				System.out.println( u.getProtocol());
				System.out.println( u.getHost() );
				System.out.println( u.getPort() );
				System.out.println(u.getPath());
				System.out.println(u.getRef());
				System.out.println( u.getQuery() );
				System.out.println( u.getUserInfo() );
				System.out.println( u.getAuthority() );
				
				//in = BufferedInputStream( in);
				Reader r = new InputStreamReader( in);
				int c;
				while( (c = r.read()) != -1 ){
					System.out.print((char)c);
				}
			}catch(MalformedURLException ex ){
				System.out.println( strurl + "is not a parseable URL");
			}catch( IOException ex ){
				System.out.println(ex);
			}finally{
				try{
				if( in != null ){
					in.close();
				}
				}catch(IOException e){
					
				}
			}
		
	}
}
