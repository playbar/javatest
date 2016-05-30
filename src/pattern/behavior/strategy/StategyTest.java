package pattern.behavior.strategy;

public class StategyTest {
	public static void main(String[] args ){
		String exp = "2+8";
		ICalculator cal = new Plus();
		int result = cal.calculate(exp);
		System.out.println(result);
	}
}
