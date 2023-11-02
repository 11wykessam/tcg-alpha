package com.wykessam.tsgalpha.model.chat;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.NotNull;

/**
 * @author Samuel Wykes.
 * Represents a message sent to the chat.
 */
@Getter
@Builder
@Jacksonized
public class ChatMessage {

    @NotNull
    @NonNull
    private final String message;

    @Override
    public String toString() {
        return this.message;
    }

}
