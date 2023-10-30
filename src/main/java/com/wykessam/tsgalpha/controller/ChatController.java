package com.wykessam.tsgalpha.controller;

import com.wykessam.tsgalpha.model.chat.ChatMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;

/**
 * @author Samuel Wykes.
 * Controller used to process requests related to chat messages.
 */
@Controller
@Slf4j
public class ChatController {

    @MessageMapping("/sendMessage")
    public Flux<ChatMessage> sendMessage(@Validated final String message) {
        log.info(message.toString());
        return Flux.just(ChatMessage.builder().message(message).build());
    }

}
