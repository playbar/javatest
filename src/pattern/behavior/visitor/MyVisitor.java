package pattern.behavior.visitor;

public class MyVisitor implements Visitor_A{
	
	public void visit(Subject sub ){
		System.out.println("visit the subject: " + sub.getSubject());
	}

}
