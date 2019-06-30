package net.ddns.popone.configs;

import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.kafka.support.TopicPartitionInitialOffset;

import net.ddns.popone.messaging.MyListener;

@EnableKafka
@Configuration
public class ConsumingConfig {
	private static final Logger LOGGER = LoggerFactory.getLogger(ConsumingConfig.class);

	@Autowired
	private KafkaAppProperties properties;
	
	@Autowired 
	private MyListener myListener;

	@Bean
	public ConsumerFactory<?, ?> kafkaConsumerFactory(KafkaProperties properties) {
		Map<String, Object> consumerProperties = properties.buildConsumerProperties();
		consumerProperties.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, 15000);

		return new DefaultKafkaConsumerFactory<>(consumerProperties);
	}

	/**
	 * è assolutamente necessario settare il MessageListener in ContainerProperties
	 */
	@Bean
	public KafkaMessageListenerContainer<String, String> container(ConsumerFactory<String, String> kafkaConsumerFactory) {
		ContainerProperties containerProps = new ContainerProperties(new TopicPartitionInitialOffset(this.properties.getTopic(), 0));
		containerProps.setMessageListener(myListener);
		
		KafkaMessageListenerContainer<String, String> container = new KafkaMessageListenerContainer<>(kafkaConsumerFactory,
				containerProps);
		
		return container;
	}

}
