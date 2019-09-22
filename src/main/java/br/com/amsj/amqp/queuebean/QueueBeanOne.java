package br.com.amsj.amqp.queuebean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author ailtonmsj
 *
 */
@Component("queueBeanOne")
public class QueueBeanOne extends QueueBeanTemplate {

	QueueBeanOne(
			@Value("${amqp.queue.queueOne.name}") String name, 
			@Value("${amqp.queue.queueOne.durable}") boolean isDurable){
		super(name, isDurable);
	}
}
