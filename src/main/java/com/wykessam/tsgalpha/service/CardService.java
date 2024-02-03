package com.wykessam.tsgalpha.service;

import com.wykessam.tsgalpha.dto.card.CardDTO;
import com.wykessam.tsgalpha.dto.card.CardDTO.CardDTOBuilder;
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

    /**
     * Convert card object to its DTO representation.
     *
     * @param card {@link Card}.
     * @return {@link CardDTO}.
     */
    public Mono<CardDTO> toDTO(final Card card) {
        return Mono.just(CardDTO.builder())
                .flatMap(builder -> this.enrichWithId(builder, card))
                .map(CardDTOBuilder::build);
    }

    private Mono<CardDTOBuilder> enrichWithId(final CardDTOBuilder builder, final Card card) {
        return Mono.just(builder.id(card.getId()));
    }

}
