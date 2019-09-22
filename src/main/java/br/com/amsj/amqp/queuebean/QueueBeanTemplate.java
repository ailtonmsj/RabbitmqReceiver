package br.com.amsj.amqp.queuebean;

public abstract class QueueBeanTemplate {
	
	protected QueueBeanTemplate(String name, boolean isDurable) {
		this.name = name;
		this.isDurable = isDurable;
	}
	
	final protected String name;
	
	final protected boolean isDurable;

	public final boolean isDurable() {
		return isDurable;
	}

	public final String getName() {
		return name;
	}
}
