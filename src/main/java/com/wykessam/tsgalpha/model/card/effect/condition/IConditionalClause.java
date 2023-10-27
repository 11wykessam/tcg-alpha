package com.wykessam.tsgalpha.model.card.effect.condition;

import com.wykessam.tsgalpha.api.request.EffectResolutionRequestV1;
import reactor.core.publisher.Mono;

/**
 * @author Samuel Wykes.
 * Reprsents a condition in a card clause.
 */
public interface IConditionalClause {

    /**
     * Resolve the condition based on the current state of the game.
     * @param request {@link EffectResolutionRequestV1}.
     * @return {@link Boolean}.
     */
    Mono<Boolean> resolve(final EffectResolutionRequestV1 request);

}
