package br.com.amsj.amqp.listener;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

public class RabbitmqMessageListener implements MessageListener {

	@Override
	public void onMessage(Message message) {

		System.out.println("Message receive: " + new String(message.getBody()));
	}
}
