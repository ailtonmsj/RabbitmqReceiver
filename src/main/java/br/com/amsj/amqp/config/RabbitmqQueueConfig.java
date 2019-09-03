package br.com.amsj.amqp.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.amsj.amqp.listener.RabbitmqMessageListener;

@Configuration
public class RabbitmqQueueConfig {
	
	private static final String TESTE_QUEUE_1 = "teste_queue_1";
	private static final String RABBITMQ_URL = "localhost";
	private static final String RABBITMQ_USERNAME = "guest";
	private static final String RABBITMQ_PASSWORD = "guest";
	
	
	@Bean
	Queue testQueue() {
		return new Queue(TESTE_QUEUE_1);
	}
	
	@Bean
	ConnectionFactory connectionFactory() {
		
		CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(RABBITMQ_URL);
		cachingConnectionFactory.setUsername(RABBITMQ_USERNAME);
		cachingConnectionFactory.setPassword(RABBITMQ_PASSWORD);
		
		return cachingConnectionFactory;
	}
	
	@Bean
	MessageListenerContainer messageListenerContainer() {
		
		SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
		simpleMessageListenerContainer.setConnectionFactory(connectionFactory());
		simpleMessageListenerContainer.setQueues(testQueue());
		simpleMessageListenerContainer.setMessageListener(new RabbitmqMessageListener());
		return simpleMessageListenerContainer;
	}

}
