package pattern.behavior.visitor;

public class MySubject implements Subject {
	
	public void accept(Visitor visitor ){
		visitor.visit(this);
	}
	
	@Override
	public String getSubject(){
		return "love";
	}
}
