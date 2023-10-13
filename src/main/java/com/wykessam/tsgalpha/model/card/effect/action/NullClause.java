package com.wykessam.tsgalpha.model.card.effect.action;

import com.wykessam.tsgalpha.api.request.EffectResolutionRequestV1;
import com.wykessam.tsgalpha.api.response.EffectResolutionResponseV2;
import com.wykessam.tsgalpha.model.card.effect.ResolutionState;
import reactor.core.publisher.Mono;

/**
 * @author Samuel Wykes.
 * Represents the terminating clause in an effect (i.e does nothing).
 */
public class NullClause implements IActionClause {

    @Override
    public String toString() {
        return "NULL";
    }

    @Override
    public ResolutionState getResolutionState() {
        return null;
    }

    @Override
    public Mono<EffectResolutionResponseV2> resolve(EffectResolutionRequestV1 request) {
        return null;
    }

    @Override
    public Mono<Void> reset() {
        return null;
    }
}
