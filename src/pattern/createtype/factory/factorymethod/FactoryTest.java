package pattern.createtype.factory.factorymethod;

import pattern.createtype.*;

public class FactoryTest {
	public static void main(String[] args ){
		SendFactory factory = new SendFactory();
		Sender sender = factory.produce("sms");
		sender.Send();
		
		sender = factory.produceMail();
		sender.Send();
	}
}
