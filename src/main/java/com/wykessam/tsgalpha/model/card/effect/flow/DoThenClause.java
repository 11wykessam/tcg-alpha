package com.wykessam.tsgalpha.model.card.effect.flow;

import com.wykessam.tsgalpha.api.request.EffectResolutionRequestV1;
import com.wykessam.tsgalpha.api.response.EffectResolutionResponseV2;
import com.wykessam.tsgalpha.model.card.effect.ResolutionState;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotNull;

import static com.wykessam.tsgalpha.model.card.effect.ResolutionState.READY;
import static com.wykessam.tsgalpha.model.card.effect.ResolutionState.RESOLVED;

/**
 * @author Samuel Wykes.
 */
@Getter
@Builder
public class DoThenClause<T extends IFlowClause, U extends IFlowClause> implements IFlowClause {

    @NotNull
    @NonNull
    private final T firstClause;

    @NotNull
    @NonNull
    private final U secondClause;

    @NonNull
    @Builder.Default
    private ResolutionState resolutionState = READY;

    @Override
    public String toString() {
        return String.format(
                "DO %s THEN %s",
                this.firstClause,
                this.secondClause
        );
    }

    /**
     * Get the current state of the clause's resolution.
     *
     * @return {@link ResolutionState}.
     */
    @Override
    @NonNull
    public ResolutionState getResolutionState() {
        return this.resolutionState;
    }

    /**
     * Resolve the effect.
     * Resolve the first effect, if successful, resolve the second.
     * @param request {@link EffectResolutionRequestV1}.
     * @return {@link EffectResolutionResponseV2}.
     */
    @Override
    public Mono<EffectResolutionResponseV2> resolve(final EffectResolutionRequestV1 request) {
        return resolveSubClause(request, this.firstClause)
                .flatMap(response ->
                        response.getResolutionState().equals(RESOLVED)
                                ? resolveSubClause(request, this.secondClause)
                                : Mono.just(response)
                )
                .doOnNext(response -> this.resolutionState = response.getResolutionState());
    }

    private static Mono<EffectResolutionResponseV2> resolveSubClause(
            final EffectResolutionRequestV1 request, final IFlowClause clause) {
        return clause.getResolutionState().equals(RESOLVED)
                ? Mono.just(EffectResolutionResponseV2.success())
                : clause.resolve(request);
    }

    /**
     * Reset the clause to the ready state.
     * @return {@link Void}.
     */
    @Override
    public Mono<Void> reset() {
        this.resolutionState = READY;
        return this.firstClause.reset()
                .then(this.secondClause.reset());
    }
}
