package pattern.behavior.visitor;

public class MyVisitor implements Visitor{
	
	public void visit(Subject sub ){
		System.out.println("visit the subject: " + sub.getSubject());
	}

}
