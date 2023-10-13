package com.wykessam.tsgalpha.model.card.effect.flow;

import com.wykessam.tsgalpha.api.request.EffectResolutionRequestV1;
import com.wykessam.tsgalpha.api.response.EffectResolutionResponseV2;
import com.wykessam.tsgalpha.model.card.effect.ResolutionState;
import com.wykessam.tsgalpha.model.card.effect.condition.IConditionalClause;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotNull;

import static com.wykessam.tsgalpha.model.card.effect.ResolutionState.READY;

/**
 * @author Samuel Wykes.
 */
@Getter
@Builder
public class IfThenElseClause<T extends IConditionalClause, U extends IFlowClause, V extends IFlowClause>
        implements IFlowClause{

    @NonNull
    @NotNull
    private final T condition;

    @NonNull
    @NotNull
    private final U thenClause;

    @NonNull
    @NotNull
    private final V elseClause;

    @NonNull
    @Builder.Default
    private ResolutionState resolutionState = READY;

    @Override
    public String toString() {
        return String.format(
                "IF %s THEN %s ELSE %s",
                this.condition,
                this.thenClause,
                this.elseClause
        );
    }

    @Override
    public ResolutionState getResolutionState() {
        return this.resolutionState;
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
