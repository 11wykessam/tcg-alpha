package com.wykessam.tsgalpha.model.card.effect.flow;

import com.wykessam.tsgalpha.api.request.EffectResolutionRequestV1;
import com.wykessam.tsgalpha.api.response.EffectResolutionResponseV2;
import com.wykessam.tsgalpha.model.card.effect.ResolutionState;
import com.wykessam.tsgalpha.model.card.effect.action.IActionClause;
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
public class DoClause<T extends IActionClause> implements IFlowClause {

    @NotNull
    @NonNull
    private final T action;

    @NonNull
    @Builder.Default
    private ResolutionState resolutionState = READY;

    @Override
    public String toString() {
        return String.format(
                "DO %s",
                this.action
        );
    }

    /**
     * Get the current state of the clause's resolution.
     * @return {@link ResolutionState}.
     */
    @Override
    @NonNull
    public ResolutionState getResolutionState() {
        return this.resolutionState;
    }

    /**
     * Resolve the clause.
     * Resolve the action clause.
     * @param request {@link EffectResolutionRequestV1}.
     * @return {@link EffectResolutionResponseV2}.
     */
    @Override
    public Mono<EffectResolutionResponseV2> resolve(final EffectResolutionRequestV1 request) {
        this.resolutionState = READY;
        return this.action.resolve(request)
                .doOnNext(response -> {
                    this.resolutionState = response.getResolutionState();
                });
    }

    /**
     * Reset the clause to the ready state.
     * @return {@link Void}.
     */
    @Override
    public Mono<Void> reset() {
        this.resolutionState = READY;
        return this.action.reset();
    }
}
