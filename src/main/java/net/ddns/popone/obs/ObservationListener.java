package net.ddns.popone.obs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import net.ddns.popone.messaging.MyListener;

@Component
public class ObservationListener {
	private static final Logger log = LoggerFactory.getLogger(ObservationListener.class);

	
	@KafkaListener(groupId = "gruppo1", topics = "test")
	public void listen(String data) {
		log.info("Ricevuto da listen: " + data);
	}
}
