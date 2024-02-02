package com.wykessam.tsgalpha.persistence.entity.Player;

import com.wykessam.tsgalpha.persistence.entity.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

/**
 * @author Samuel Wykes.
 * Represents a player in a specific game of the TCG.
 * This differs to {@link User} in that an instance is unique to a game instance, but is still linked to alogged-in user.
 */
@Document(collection = "player")
@Getter
@Builder
@RequiredArgsConstructor
public class Player implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Builder.Default
    private final UUID id = UUID.randomUUID();

}
