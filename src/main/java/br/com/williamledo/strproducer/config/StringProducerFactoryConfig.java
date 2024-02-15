package br.com.williamledo.strproducer.config;

import java.util.HashMap;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class StringProducerFactoryConfig { // Quando o produtor envia uma mensagem para o kafka ela é serializada, é convertida em bytes e é armazenada no kafka, essa classe fará a função de serializar

	private final KafkaProperties properties;
	
	@Bean
	public ProducerFactory<String, String> producerFactory() {
		
		var configs = new HashMap<String, Object>();
		
		configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, properties.getBootstrapServers()); //Adicionando as configurações dos servers que está no application.properties.yml
		configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class); // Usado para serializar as chaves que são string  -  KEY) / CHAVE
		configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class); // Usado para serializar as os valores da mensagens que são strings  - VALUE VALOR
		
		return new DefaultKafkaProducerFactory<>(configs);
		
	}
	
	@Bean
	public KafkaTemplate<String, String> kafkaTemplate(ProducerFactory<String,String> producerFactory) {
		
		return new KafkaTemplate<>(producerFactory);
		
	}
	
}
