package pattern.behavior.responsibility;

public class AbstractHandler {
	private Handler handler = null;
	
	public Handler getHandler(){
		return handler;
	}
	
	public void setHandler( Handler handler ){
		this.handler = handler;
	}
}
