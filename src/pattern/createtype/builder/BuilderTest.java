package pattern.createtype.builder;

public class BuilderTest {
	public static void main( String[]args ){
		Builder buidler = new Builder();
		buidler.produceMailSender(10);
	}
}
