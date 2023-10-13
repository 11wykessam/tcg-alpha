package com.wykessam.tsgalpha.model.card.effect.condition;

import com.wykessam.tsgalpha.api.request.EffectResolutionRequestV1;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotNull;

/**
 * @author Samuel Wykes.
 * Represents an 'and' combination of two conditional clauses.
 */
@Getter
@Builder
public class AndClause<T extends IConditionalClause, U extends IConditionalClause> implements IConditionalClause{

    @NotNull
    @NonNull
    private final T firstCondition;

    @NotNull
    @NonNull
    private final U secondCondition;

    @Override
    public String toString() {
        return String.format(
                "%s AND %s",
                this.firstCondition,
                this.secondCondition
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
                .all(bool -> bool);
    }
}
