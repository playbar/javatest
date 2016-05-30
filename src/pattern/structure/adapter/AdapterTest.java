package pattern.structure.adapter;

public class AdapterTest {
	public static void main( String[] args ){
		Targetable target = new ClassAdapter();
		target.method1();
		target.method2();
	}
}
