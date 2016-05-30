package pattern.behavior.mediator;

public class MediatorImp extends AbstractMediator {
	public MediatorImp( AbstractColleague a, AbstractColleague b ){
		super( a, b );
	}
	
	public void AaffectB(){
		int number = A.getNumber();
		B.setNumber( number * 100 );
	}
	
	public void BaffectA(){
		int number = B.getNumber();
		A.setNumber(number / 100 );
	}
}
