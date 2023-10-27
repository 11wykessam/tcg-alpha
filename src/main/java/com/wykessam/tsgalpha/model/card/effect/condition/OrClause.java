package com.wykessam.tsgalpha.model.card.effect.condition;

import com.wykessam.tsgalpha.api.request.EffectResolutionRequestV1;
import lombok.Builder;
import lombok.Getter;
import reactor.core.publisher.Mono;

/**
 * @author Samuel Wykes.
 * Represents an 'or' combination of two conditional clauses.
 */
@Getter
@Builder
public class OrClause<T extends IConditionalClause, U extends IConditionalClause> implements IConditionalClause {

    private final T firstCondition;

    private final U secondCondition;

    @Override
    public String toString() {
        return String.format(
                "%s OR %s",
                this.firstCondition.toString(),
                this.secondCondition.toString()
        );
    }

    /**
     * Resolves the condition based on the state of the game.
     * @param request {@link EffectResolutionRequestV1}.
     * @return {@link Boolean}.
     */
    @Override
    public Mono<Boolean> resolve(final EffectResolutionRequestV1 request) {
        return this.firstCondition.resolve(request)
                .concatWith(this.secondCondition.resolve(request))
                .any(bool -> bool);
    }

}
