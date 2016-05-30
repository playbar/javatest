package pattern.createtype.factory.AbstractFactory;

import pattern.createtype.*;

public class SendSmsFactory implements Provider {
	public Sender produce(){
		return new SmsSender();
	}
}
