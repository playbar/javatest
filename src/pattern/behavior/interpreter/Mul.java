package pattern.behavior.interpreter;

public class Mul implements Expression {
	@Override
	public int interpret( Context context ){
		return context.getNum1() * context.getNum2();
	}
}
