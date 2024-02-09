package com.wykessam.tsgalpha.persistence.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

/**
 * @author Samuel Wykes.
 */
@DataMongoTest
class CardDBRepositoryTest {

    private static final String CARD_NAME = "name";

    @Autowired
    private ChampionCardDBRepository championCardDBRepository;

    @Autowired
    private CreatureCardDBRepository creatureCardDBRepository;

    @Autowired
    private DisruptionCardDBRepository disruptionCardDBRepository;

    @Autowired
    private CardDBRepository cardDBRepository;

//    @Test
//    void polymorphismTest() {
//        final ChampionCard championCard = ChampionCard.builder()
//                .originalName(CARD_NAME)
//                .build();
//
//        StepVerifier.create(this.cardDBRepository.deleteAll()
//                .then(this.championCardDBRepository.save(championCard))
//                .thenMany(this.cardDBRepository.findAll()))
//                .expectNextCount(1)
//                .verifyComplete();
//    }
//
//    private static ChampionCard getChampionCard() {
//        return ChampionCard.builder()
//                .
//    }

}