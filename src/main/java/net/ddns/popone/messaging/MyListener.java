package net.ddns.popone.messaging;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.stereotype.Component;

@Component
public class MyListener implements MessageListener<String, String>{
	private static final Logger log = LoggerFactory.getLogger(MyListener.class);

	@Override
	public void onMessage(ConsumerRecord<String, String> data) {
		log.info("Ricevuto da OnMessage: " + data);		
	}
}
