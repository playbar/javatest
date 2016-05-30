package pattern.createtype.factory.AbstractFactory;

import pattern.createtype.Sender;
import pattern.createtype.factory.*;;

public interface Provider {
	public Sender produce();
}
