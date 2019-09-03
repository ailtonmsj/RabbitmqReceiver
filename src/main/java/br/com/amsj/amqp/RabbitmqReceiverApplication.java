package br.com.amsj.amqp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RabbitmqReceiverApplication {

	public static void main(String[] args) {
		SpringApplication.run(RabbitmqReceiverApplication.class, args);
	}

}
