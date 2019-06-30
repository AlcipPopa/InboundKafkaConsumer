package net.ddns.popone.messaging;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.AcknowledgingMessageListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class MyListener implements AcknowledgingMessageListener<String, String>{
	private static final Logger log = LoggerFactory.getLogger(MyListener.class);
	private String nulla;

	@Override
	@KafkaListener(groupId = "gruppo1", topics = "test")
	public void onMessage(ConsumerRecord<String, String> data, Acknowledgment acknowledgment) {
		log.info("Ricevuto da OnMessage: " + data);
		//nulla.length();
		
		// metodo che serve per commit manuale, per indicare che è stato ricevuto tutto
		acknowledgment.acknowledge();
	}
}
