package pattern.behavior.interpreter;

public class Interpreter {

	public static void main(String[] args ){
		
		int re = new Plus().interpret(new Context(9, 2));
        int result = new Minus().interpret((new Context( re, 8))); 
        result = new Mul().interpret( new Context( re, 8 ));
        System.out.println(result);  
	}
}
