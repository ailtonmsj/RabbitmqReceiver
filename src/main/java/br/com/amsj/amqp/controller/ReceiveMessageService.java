package br.com.amsj.amqp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class ReceiveMessageService {
	
	
	@RequestMapping(value="/message", method=RequestMethod.GET)
	public @ResponseBody String getNextMessage() {
		
		return "test";
	}

}
