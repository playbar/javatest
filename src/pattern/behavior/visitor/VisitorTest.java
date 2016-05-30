package pattern.behavior.visitor;

public class VisitorTest {
	public static void main(String[] args ){
		Visitor visitor = new MyVisitor();
		Subject sub = new MySubject();
		sub.accept(visitor);
	}
}
