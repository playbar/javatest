package pattern.behavior.observe;

public class MySubject extends AbstractSubject{
	
	@Override
	public void operation(){
		System.out.println( "udpate self");
		this.notifyObservers();
	}

}
