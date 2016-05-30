package pattern.createtype.factory.AbstractFactory;

import pattern.createtype.*;


public class SendMailFactory implements Provider {
	public Sender produce(){
		return new MailSender();
	}
}
