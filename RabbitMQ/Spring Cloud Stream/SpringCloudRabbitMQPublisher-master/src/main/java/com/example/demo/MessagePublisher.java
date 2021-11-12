package com.example.demo;



import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@EnableBinding(Source.class)
public class MessagePublisher {

	@Autowired
	Source source;
	
	@PostMapping(value="/txn")
	public String sendMessage(@RequestBody String payload) {
		ObjectMapper ob = new ObjectMapper();
		Transaction txn = null;
		source.output().send(MessageBuilder.withPayload(txn).setHeader("myheader", "myheaderValue").build());
		System.out.println("Successfully sent to rabbitmq");
		return "success";
	}
}
