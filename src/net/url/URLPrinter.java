package net.url;

import java.io.*;
import java.net.*;

public class URLPrinter {
	public static void main( String[] args ){
		try{
			URL u = new URL("http://www.oreilly.com/");
			URLConnection uc = u.openConnection();
			if( !uc.getDoInput() ){
				uc.setDoInput( true );
			}
			uc.setAllowUserInteraction( true );
			InputStream in = uc.getInputStream();
			System.out.println( uc.getURL() );
		}catch( IOException ex ){
			System.out.println( ex );
		}
	}
}
