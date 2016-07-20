package pattern.structure.decorator;

public class ConcreateDecorator1 extends DecoratorComponent {

	public ConcreateDecorator1(Component component) {
		super(component);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void functionA(){
		super.functionA();
		this.functionB();
	}
	
	private void functionB(){
		System.out.println("ConcreateDecorator1->function1");
	}

}
