package br.com.danilomr.kafka.controller;

import br.com.danilomr.kafka.controller.dto.MessageRequestDTO;
import br.com.danilomr.kafka.producer.MessageProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(path = "/message")
public class MessageController {

    @Autowired
    private MessageProducer messageProducer;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/topic-1")
    void topic1(@RequestBody MessageRequestDTO request) {
        log.debug(String.format("Receiving message on topic-1 endpoint; MESSAGE=[%s]", request.getMessage()));
        messageProducer.produceGroup1(request.getMessage());
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/topic-2")
    void topic2(@RequestBody MessageRequestDTO request) {
        log.debug(String.format("Receiving message on topic-2 endpoint; MESSAGE=[%s]", request.getMessage()));
        messageProducer.produceGroup2(request.getMessage());
    }
}
