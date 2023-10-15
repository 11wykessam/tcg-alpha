package com.wykessam.tsgalpha.model.chat;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;
import org.springframework.stereotype.Controller;

/**
 * @author Samuel Wykes.
 * Represents a message sent to the chat.
 */
@Getter
@Builder
@Jacksonized
public class ChatMessage {

    private final String message;

    @Override
    public String toString() {
        return this.message;
    }

}
