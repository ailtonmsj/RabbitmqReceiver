package br.com.amsj.amqp.controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.Delivery;

import br.com.amsj.amqp.queuebean.QueueBeanTemplate;

@RestController
@RequestMapping("/")
public class ReceiveMessageService {
	
	@Autowired
	ConnectionFactory connectionFactory;
	
	@Autowired
	private QueueBeanTemplate queueBeanTwo;

	@RequestMapping(value = "/message", method = RequestMethod.GET)
	public @ResponseBody String getNextMessage() {
		
		String message = "";
		Channel channel = connectionFactory.createConnection().createChannel(true);

		DeliverCallback deliverCallback = new DeliverCallback() {
			
			String message = "";
			
			@Override
			public void handle(String consumerTag, Delivery delivery) throws IOException {
				message = new String(delivery.getBody(), StandardCharsets.UTF_8);
				System.out.println("GET QUEUE MESSAGE: " + message);
				System.out.println("delivery.getEnvelope().getDeliveryTag(): " + delivery.getEnvelope().getDeliveryTag());
				
				channel.basicAck(delivery.getEnvelope().getDeliveryTag(), true);
			}
		};
			
		
		try {

			channel.basicQos(1);
			
//			message = channel.basicConsume(this.queueBeanTwo.getName(), deliverCallback, new ConsumerShutdownSignalCallback() {
//			message = channel.basicConsume(this.queueBeanTwo.getName(), true, deliverCallback, new ConsumerShutdownSignalCallback() {
			message = channel.basicConsume(this.queueBeanTwo.getName(), false, deliverCallback, consumerTag -> {});

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return message;
	}

}
