package pattern.behavior.memento;

public class MementoTest {
	public static void main( String[] args ){
		Original origin = new Original("egg");
		
		Storage storage = new Storage( origin.createMemento());
		System.out.println("初始化状态为:" + origin.getValue() );
		origin.setValue("niu");
		System.out.println("修改后的状态为:" + origin.getValue() );
		
		origin.restoreMemento(storage.getMemento());
		System.out.println("恢复后的状态:" + origin.getValue());
		return;
	}
}
