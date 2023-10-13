package com.wykessam.tsgalpha.model.card.effect.action;

import com.wykessam.tsgalpha.api.request.EffectResolutionRequestV1;
import com.wykessam.tsgalpha.api.response.EffectResolutionResponseV2;
import com.wykessam.tsgalpha.model.card.effect.ResolutionState;
import reactor.core.publisher.Mono;

import static com.wykessam.tsgalpha.model.card.effect.ResolutionState.RESOLVED;

/**
 * @author Samuel Wykes.
 * Represents the terminating clause in an effect (i.e does nothing).
 */
public class NullClause implements IActionClause {

    @Override
    public String toString() {
        return "NULL";
    }

    /**
     * Get the current state of the clause's resolution.
     *
     * @return {@link ResolutionState}.
     */
    @Override
    public ResolutionState getResolutionState() {
        return RESOLVED;
    }

    /**
     * Resolve the clause.
     * Returns a successful effect resolution.
     *
     * @param request {@link EffectResolutionRequestV1}.
     * @return {@link EffectResolutionResponseV2}.
     */
    @Override
    public Mono<EffectResolutionResponseV2> resolve(final EffectResolutionRequestV1 request) {
        return Mono.just(EffectResolutionResponseV2.success());
    }

    /**
     * Reset the clause to the ready state.
     * Nothing is needed here.
     *
     * @return {@link Void}.
     */
    @Override
    public Mono<Void> reset() {
        return Mono.empty();
    }
}
