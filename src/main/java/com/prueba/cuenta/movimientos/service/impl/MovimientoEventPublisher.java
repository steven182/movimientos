package com.prueba.cuenta.movimientos.service.impl;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.prueba.cuenta.movimientos.config.MovimientoRegistradoEvent;

@Service
public class MovimientoEventPublisher {
	
	private final RabbitTemplate rabbitTemplate;

    public MovimientoEventPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void enviarMovimientoRegistradoEvento(MovimientoRegistradoEvent evento) {
        rabbitTemplate.convertAndSend("movimientoExchange", "movimiento.creado", evento);
    }

}
