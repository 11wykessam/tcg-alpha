package com.wykessam.tsgalpha.service;

import com.wykessam.tsgalpha.exception.CardNotFoundException;
import com.wykessam.tsgalpha.persistence.entity.card.Card;
import com.wykessam.tsgalpha.persistence.repository.CardDBRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * @author Samuel Wykes.
 * Service responsible for handling logic involving {@link Card} objects.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class CardService {

    private final CardDBRepository cardDBRepository;

    /**
     * Get a card by its unique identifier.
     *
     * @param id {@link UUID}.
     * @return {@link Card}.
     */
    public Mono<Card> getById(final UUID id) {
        return this.cardDBRepository.findById(id)
                .switchIfEmpty(Mono.error(new CardNotFoundException(id)));
    }

}
