package pattern.structure.decorator;

public class ConcreateDecorator2 extends DecoratorComponent {

	public ConcreateDecorator2(Component component) {
		super(component);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void functionA()
	{
		super.functionA();
		this.functionC();
	}
	
	private void functionC(){
		System.out.println("ConcreateDecorator2->function2");
	}

}
