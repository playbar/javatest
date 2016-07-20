package pattern.structure.decorator;

public class DecoratorTest1 {
	public static void main(String[] args ){
		Component component = new ConcreateDecorator1(new ConcreateComponent());
		component.functionA();
		return;
	}
}
