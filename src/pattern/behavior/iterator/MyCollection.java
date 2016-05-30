package pattern.behavior.iterator;

public class MyCollection implements Collection {
	public String string[] = {"A", "B", "C", "D", "E"};
	public Iterator iterator(){
		return new MyIterator(this);
	}
	public Object get(int i){
		return string[i];
	}
	
	public int size(){
		return string.length;
	}
	
}
