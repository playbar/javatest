package pattern.structure.decorator;

public class DecoratorComponent implements Component {
	
	private Component component;
	public DecoratorComponent( Component component ){
		this.component = component;
	}
	
	public void functionA(){
		component.functionA();
	}
	
}
