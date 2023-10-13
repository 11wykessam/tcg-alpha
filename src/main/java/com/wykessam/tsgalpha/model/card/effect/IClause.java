package com.wykessam.tsgalpha.model.card.effect;

import com.wykessam.tsgalpha.api.request.EffectResolutionRequestV1;
import com.wykessam.tsgalpha.api.response.EffectResolutionResponseV2;
import reactor.core.publisher.Mono;

/**
 * @author Samuel Wykes.
 * Represents a clause in a card effect.
 */
public interface IClause {

    /**
     * Get the current resolution state of the clause.
     * @return {@link ResolutionState}.
     */
    ResolutionState getResolutionState();

    /**
     * Attempts to resolve the clause.
     * @param request {@link EffectResolutionRequestV1}.
     * @return {@link EffectResolutionResponseV2}.
     */
    Mono<EffectResolutionResponseV2> resolve(final EffectResolutionRequestV1 request);

    /**
     * Attempt to reset the clause to ready state.
     * @return {@link Void}.
     */
    Mono<Void> reset();

}
