package pattern.behavior.mediator;

public class MediatorTest {
	public static void main(String[] args ){
		Mediator mediator = new MyMediator();
		mediator.createMediator();
		mediator.workall();
	}
}
