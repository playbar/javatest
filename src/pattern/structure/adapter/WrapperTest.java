package pattern.structure.adapter;

public class WrapperTest {
	public static void main(String []args){

	}
	
	public void ClassApadte(){
		Targetable target = new ClassAdapter();
		target.method1();
		target.method2();
	}
	
	public void ObjectAdaper(){
		Source source = new Source();
		Targetable target = new Wrapper(source);
		target.method1();
		target.method2();
	}
	
	public void InterfaceAdapter(){
		Sourceable source1 = new SourceSub1();
		Sourceable source2 = new SourceSub2();
		
		source1.method1();
		source1.method2();
		source2.method1();
		source2.method2();
	}
}
