package pattern.behavior.mediator;

public abstract class AbstractColleague {
	
	protected int number;
	public int getNumber(){
		return number;
	}
	
	public void setNumber( int number ){
		this.number = number;
	}
	
	public abstract void setNumber( int number, AbstractMediator coll);
}
