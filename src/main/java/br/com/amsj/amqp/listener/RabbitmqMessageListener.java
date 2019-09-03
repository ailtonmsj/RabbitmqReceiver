package br.com.amsj.amqp.listener;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

public class RabbitmqMessageListener implements MessageListener {

	@Override
	public void onMessage(Message message) {
		
		if(message == null) {
			System.out.println("Message is null");
		}else {
			System.out.println("Message receive: " + new String(message.getBody()));
		}
	}
}
