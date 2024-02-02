package com.wykessam.tsgalpha.controller;

import com.wykessam.tsgalpha.dto.chat.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

/**
 * @author Samuel Wykes.
 * Controller used to process requests related to chat messages.
 */
@Controller
public class ChatController {

    @MessageMapping("send.message")
    public Mono<ChatMessage> sendMessage(@Validated final ChatMessage message) {
        return Mono.just(message);
    }

}
