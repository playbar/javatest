package pattern.createtype.singleton;

import java.util.Vector;

public class SingletonTest {
	private static SingletonTest instance = null;
	private Vector properties = null;
	
	public Vector getProperties(){
		return properties;
	}
	
	private SingletonTest(){
		
	}
	
	private static synchronized void syncInit(){
		if( null == instance ){
			instance = new SingletonTest();
		}
	}
	
	public static SingletonTest getInstance(){
		if( null == instance ){
			syncInit();
		}
		return instance;
	}
	
	public void updateProperties(){
		SingletonTest shadow = new SingletonTest();
		properties = shadow.getProperties();
	}
	
}
