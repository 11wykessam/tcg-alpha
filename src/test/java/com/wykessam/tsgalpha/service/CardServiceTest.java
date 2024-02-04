package com.wykessam.tsgalpha.service;

import com.wykessam.tsgalpha.exception.CardNotFoundException;
import com.wykessam.tsgalpha.persistence.entity.card.Card;
import com.wykessam.tsgalpha.persistence.entity.card.ChampionCard;
import com.wykessam.tsgalpha.persistence.repository.CardDBRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/**
 * @author Samuel Wykes.
 */
@ExtendWith(MockitoExtension.class)
class CardServiceTest {

    private static final UUID ID = UUID.randomUUID();
    private static final String ORIGINAL_NAME = "NAME";

    @Mock
    private CardDBRepository cardDBRepository;

    @InjectMocks
    private CardService cardService;

    @Test
    void getByIdSuccess(
            @Mock final Card card
    ) {
        when(this.cardDBRepository.findById(ID))
                .thenReturn(Mono.just(card));

        StepVerifier.create(this.cardService.getById(ID))
                .assertNext(response -> assertThat(response).isEqualTo(card))
                .verifyComplete();
    }

    @Test
    void getByIdNotFound() {
        when(this.cardDBRepository.findById(ID))
                .thenReturn(Mono.empty());

        StepVerifier.create(this.cardService.getById(ID))
                .verifyErrorSatisfies(error -> assertThat(error instanceof CardNotFoundException).isTrue());
    }

    @Test
    void championToDTOSuccess() {
        final Card card = ChampionCard.builder()
                .id(ID)
                .originalName(ORIGINAL_NAME)
                .build();

        StepVerifier.create(this.cardService.toDTO(card))
                .assertNext(cardDTO -> {
                    assertThat(cardDTO.getId()).isEqualTo(ID);
                })
                .verifyComplete();
    }

}