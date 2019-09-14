package br.com.amsj.amqp.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.amsj.amqp.listener.RabbitmqMessageListener;

@Configuration
public class RabbitmqQueueConfig {
	
	public RabbitmqQueueConfig (@Value("${amqp.host}") String rabbitmqHost,
			@Value("${amqp.port}") Integer rabbitmqPort,
			@Value("${amqp.username}") String rabbitmqUsername,
			@Value("${amqp.password}") String rabbitmqPassword,
			@Value("${amqp.testQueue1}") String testQueue1) {
				
		this.rabbitmqHost = rabbitmqHost;
		this.rabbitmqPort = rabbitmqPort;
		this.rabbitmqUsername = rabbitmqUsername;
		this.rabbitmqPassword = rabbitmqPassword;
		this.testQueue1 = testQueue1;
	}
	
	private final String rabbitmqHost;
	private final Integer rabbitmqPort;
	private final String rabbitmqUsername;
	private final String rabbitmqPassword;
	
	private final String testQueue1;
	
	
	@Bean
	Queue testQueue1() {
		final boolean isDurable = Boolean.FALSE;
		return new Queue(testQueue1, isDurable);
	}
	
	@Bean
	ConnectionFactory connectionFactory() {
		
		CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(rabbitmqHost, rabbitmqPort);
		cachingConnectionFactory.setUsername(rabbitmqUsername);
		cachingConnectionFactory.setPassword(rabbitmqPassword);
		
		return cachingConnectionFactory;
	}
	
	@Bean
	MessageListenerContainer messageListenerContainer() {
		
		SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
		simpleMessageListenerContainer.setConnectionFactory(connectionFactory());
		simpleMessageListenerContainer.setQueues(testQueue1());
		simpleMessageListenerContainer.setMessageListener(new RabbitmqMessageListener());
		return simpleMessageListenerContainer;
	}
	

}
