package br.com.southsystem.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import br.com.southsystem.controller.PautaController;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EnviaFila {
	
	
	private static Logger log = LoggerFactory.getLogger(PautaController.class);
	private final JmsTemplate jmsTemplate = new JmsTemplate();

    @Value("${activemq.name}")
    private String destinationQueue;

    public void send(List<Object[]> resultado){
    	log.info("Enviando dados para a Fila do ActiveMQ");
        Gson gson = new Gson();
        String jsonResultado = gson.toJson(resultado);
        jmsTemplate.convertAndSend(destinationQueue, jsonResultado);
        log.info("Fim do envio de dados para a Fila do ActiveMQ");
    }
}
