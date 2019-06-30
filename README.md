# InboundKafkaConsumer
Example of Kafka Consumer in Spring Boot

Remember to modify "application.yaml" file with your options.

kafka.topic is the topic itself;
consumer.bootstrap-servers is the kafka broker endpoint: you can try it out with the default Kafka broker (from shell command) and after with a Docker cluster;
consumer.auto-offset-reset is now set to get the earliest message not committed;
consumer.enable-auto-commit is set to false, but it works only if you implement a \*Listener interface to manually commit (check it out here https://docs.spring.io/spring-kafka/reference/html/#message-listeners )
consumer.value and key deserializers: they are used to deserialize payload and key parts of a message (String is really the base... check out more at https://docs.spring.io/spring-kafka/reference/html/#serdes ).
