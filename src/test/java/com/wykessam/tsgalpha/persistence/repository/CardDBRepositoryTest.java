package com.wykessam.tsgalpha.persistence.repository;

import com.wykessam.tsgalpha.persistence.entity.card.ChampionCard;
import com.wykessam.tsgalpha.persistence.entity.card.CreatureCard;
import com.wykessam.tsgalpha.persistence.entity.card.CreatureCardType;
import com.wykessam.tsgalpha.persistence.entity.card.DisruptionCard;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.test.StepVerifier;

import java.util.UUID;

import static com.wykessam.tsgalpha.persistence.entity.card.CreatureCardType.DRAGON;

/**
 * @author Samuel Wykes.
 */
@SpringBootTest
@AutoConfigureTestEntityManager
class CardDBRepositoryTest {

    private static final UUID ID = UUID.randomUUID();
    private static final String NAME = "name";
    private static final String TEXT = "text";
    private static final Integer SUMMONING_REQUIREMENT = 1;
    private static final Integer SIZE = 2;
    private static final Integer POWER = 3;
    private static final Integer CRITICAL = 4;

    private static final CreatureCardType TYPE = DRAGON;

    @Autowired
    private ChampionCardDBRepository championCardDBRepository;

    @Autowired
    private CreatureCardDBRepository creatureCardDBRepository;

    @Autowired
    private DisruptionCardDBRepository disruptionCardDBRepository;

    @Autowired
    private CardDBRepository cardDBRepository;

    @Test
    void polymorphismTest() {
        final ChampionCard championCard = getChampionCard();
        final CreatureCard creatureCard = getCreatureCard();
        final DisruptionCard disruptionCard = getDisruptionCard();

        StepVerifier.create(this.cardDBRepository.deleteAll()
                        .then(this.cardDBRepository.save(creatureCard))
                        .then(this.cardDBRepository.save(championCard))
                        .then(this.cardDBRepository.save(disruptionCard))
                        .thenMany(this.cardDBRepository.findAll()))
                .expectNextCount(3)
                .verifyComplete();
    }

    private static ChampionCard getChampionCard() {
        return ChampionCard.builder()
                .id(ID)
                .originalName(NAME)
                .name(NAME)
                .originalText(TEXT)
                .text(TEXT)
                .originalSummoningRequirement(SUMMONING_REQUIREMENT)
                .summoningRequirement(SUMMONING_REQUIREMENT)
                .originalType(TYPE)
                .type(TYPE)
                .originalSize(SIZE)
                .size(SIZE)
                .originalPower(POWER)
                .power(POWER)
                .originalCritical(CRITICAL)
                .critical(CRITICAL)
                .build();
    }

    private static CreatureCard getCreatureCard() {
        return CreatureCard.builder()
                .id(ID)
                .originalName(NAME)
                .name(NAME)
                .originalText(TEXT)
                .text(TEXT)
                .originalSummoningRequirement(SUMMONING_REQUIREMENT)
                .summoningRequirement(SUMMONING_REQUIREMENT)
                .originalType(TYPE)
                .type(TYPE)
                .originalSize(SIZE)
                .size(SIZE)
                .originalPower(POWER)
                .power(POWER)
                .originalCritical(CRITICAL)
                .critical(CRITICAL)
                .build();
    }

    private static DisruptionCard getDisruptionCard() {
        return DisruptionCard.builder()
                .id(ID)
                .originalName(NAME)
                .name(NAME)
                .originalText(TEXT)
                .text(TEXT)
                .originalSummoningRequirement(SUMMONING_REQUIREMENT)
                .summoningRequirement(SUMMONING_REQUIREMENT)
                .build();
    }

}