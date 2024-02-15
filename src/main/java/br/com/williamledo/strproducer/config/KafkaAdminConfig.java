package br.com.williamledo.strproducer.config;

import java.util.HashMap;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor //Faz a injeção de dependencia (properties) com o construtor
@Configuration
public class KafkaAdminConfig { // Essa classe faz a configuração base do Kafka

	public final KafkaProperties properties;
	
	@Bean
	public KafkaAdmin kafkaAdmin() {
		
		var configs = new HashMap<String, Object>();
		
		configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, properties.getBootstrapServers());
		return new KafkaAdmin(configs);
		
	}
	
	@Bean
	public KafkaAdmin.NewTopics topics() { //Cria o 
		
		return new KafkaAdmin.NewTopics(
					TopicBuilder.name("str-topic").partitions(2).replicas(1).build()
				);
	}
	
}
