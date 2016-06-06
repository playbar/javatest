package pattern.behavior.visitor;

public interface Subject {
	public void accept(Visitor_A visitor );
	public String getSubject();
}
