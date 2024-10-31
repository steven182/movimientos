package com.prueba.cuenta.movimientos.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

	@Bean
	public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	@Bean
	public TopicExchange movimientoExchange() {
		return new TopicExchange("movimientoExchange");
	}

	@Bean
	public Queue movimientoQueue() {
		return new Queue("movimientoQueue", true);
	}

	@Bean
	public Binding bindingMovimientoQueue(Queue movimientoQueue, TopicExchange movimientoExchange) {
		return BindingBuilder.bind(movimientoQueue).to(movimientoExchange).with("movimiento.creado");
	}

}
