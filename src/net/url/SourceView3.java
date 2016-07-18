package net.url;

import java.io.*;
import java.net.*;

public class SourceView3 {
	
	public static void main( String[] args ){
		try{
			URL u = new URL( "http://www.oreilly.com");
			HttpURLConnection uc = (HttpURLConnection) u.openConnection();
			int code = uc.getResponseCode();
			String response = uc.getResponseMessage();
			System.out.println("HTTP/1.x " + code + " " + response );
			for( int j = 1; ; j++ ){
				String header = uc.getHeaderField(j);
				String key = uc.getHeaderFieldKey(j);
				if( header == null || key == null )
					break;
				System.out.println( uc.getHeaderFieldKey( j ) + ":" + header);
			}
			System.out.println();
			try(InputStream in = new BufferedInputStream(uc.getInputStream())){
				Reader r = new InputStreamReader( in );
				int c = 0;
				while( (c = r.read()) != -1 ){
					System.out.println((char)c);
				}
			}
		}catch(MalformedURLException ex ){
			System.err.println( " URL is not a parseable URL");
		}catch(IOException ex ){
			System.err.println(ex);
		}
	}
}
