package net.stream;

import java.io.*;
import java.util.zip.*;

public class GZipRunnable implements Runnable {

	private File input;
	public GZipRunnable(File input ){
		this.input = input;
	}
	
	public void run(){
		if( input.getName().endsWith(".gz")){
			File output = new File(input.getParent(), input.getName() + ".gz");
			if( !output.exists()){
				try(
					InputStream in = new BufferedInputStream( new FileInputStream( input));
					OutputStream out = new BufferedOutputStream(
							new GZIPOutputStream(new FileOutputStream(output)));
				){
					int b = 0;
					while((b = in.read()) != -1 ) 
						out.write(b);
					out.flush();
				}catch( IOException ex ){
					System.out.println(ex);
				}
			}
		}
	}
	
}
