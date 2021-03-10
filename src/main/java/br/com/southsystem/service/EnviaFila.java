package br.com.southsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;

import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EnviaFila {
	
	private final JmsTemplate jmsTemplate = new JmsTemplate();

    @Value("${activemq.name}")
    private String destinationQueue;

    public void send(List<ResultadoDTO> resultado){
        Gson gson = new Gson();
        String jsonResultado = gson.toJson(resultado);
        jmsTemplate.convertAndSend(destinationQueue, jsonResultado);
    }
}
