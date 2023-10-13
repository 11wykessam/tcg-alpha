package com.wykessam.tsgalpha.model.card.effect;

import com.wykessam.tsgalpha.api.response.EffectResolutionResponseV2;
import reactor.core.publisher.Mono;

/**
 * @author Samuel Wykes.
 * Represents the state of an effect or clause of an effect.
 */
public enum ResolutionState {

    READY,
    SUCCESS,
    PLAYER_INPUT_REQUIRED;
}
