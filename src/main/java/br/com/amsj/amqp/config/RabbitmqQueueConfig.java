package br.com.amsj.amqp.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.amsj.amqp.listener.RabbitmqMessageListener;
import br.com.amsj.amqp.queuebean.QueueBeanTemplate;

/**
 * @author ailtonmsj
 *
 */
@Configuration
public class RabbitmqQueueConfig {
	
	public RabbitmqQueueConfig (
			@Value("${amqp.configuration.host}") String rabbitmqHost,
			@Value("${amqp.configuration.port}") Integer rabbitmqPort,
			@Value("${amqp.configuration.username}") String rabbitmqUsername,
			@Value("${amqp.configuration.password}") String rabbitmqPassword) {
				
		this.rabbitmqHost = rabbitmqHost;
		this.rabbitmqPort = rabbitmqPort;
		this.rabbitmqUsername = rabbitmqUsername;
		this.rabbitmqPassword = rabbitmqPassword;
	}
	
	private final String rabbitmqHost;
	private final Integer rabbitmqPort;
	private final String rabbitmqUsername;
	private final String rabbitmqPassword;
	
	
	@Autowired
	private QueueBeanTemplate queueBeanOne;
	
	@Autowired
	private QueueBeanTemplate queueBeanTwo;
	
	@Bean
	Queue queueOne() {
		return QueueBuilder.nonDurable(this.queueBeanOne.getName()).build();
	}
	
	@Bean
	Queue queueTwo() {
		return new Queue(this.queueBeanTwo.getName(), this.queueBeanTwo.isDurable());
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
		// Add the queues to listen
		simpleMessageListenerContainer.setQueues(queueOne(), queueTwo());
		simpleMessageListenerContainer.setMessageListener(new RabbitmqMessageListener());
		return simpleMessageListenerContainer;
	}
}
