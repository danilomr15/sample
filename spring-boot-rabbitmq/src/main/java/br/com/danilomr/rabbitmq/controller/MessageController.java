package br.com.danilomr.rabbitmq.controller;

import br.com.danilomr.rabbitmq.controller.dto.MessageRequestDTO;
import br.com.danilomr.rabbitmq.producer.MessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/message")
public class MessageController {

    @Autowired
    private MessageProducer messageProducer;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    void sendQuene(@RequestBody MessageRequestDTO request) {
        messageProducer.send(request.getMessage());
    }
}
