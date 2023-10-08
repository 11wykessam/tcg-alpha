package com.wykessam.tsgalpha.model.card.effect.flow;

import com.wykessam.tsgalpha.model.card.effect.ResolutionState;
import com.wykessam.tsgalpha.model.card.effect.action.IActionClause;
import com.wykessam.tsgalpha.model.card.effect.action.result.ResolutionResult;
import com.wykessam.tsgalpha.model.card.effect.action.result.ResolutionResultState;
import com.wykessam.tsgalpha.model.game.IGame;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import javax.validation.constraints.NotNull;

import static com.wykessam.tsgalpha.model.card.effect.ResolutionState.*;
import static com.wykessam.tsgalpha.model.card.effect.action.result.ResolutionResultState.SUCCESS;

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
     * Resolve the clauses.
     * Resolve the first clause, if complete then resolve second clause.
     * @param game {@link IGame}.
     * @return {@link ResolutionResult}.
     */
    @Override
    public ResolutionResult resolve(final IGame game) {
        this.resolutionState = IN_PROGRESS;

        // resolve first clause if necessary.
        if (this.firstClause.getResolutionState() != COMPLETE) {
            final ResolutionResult firstResult = this.firstClause.resolve(game);
            if (firstResult.getState() != SUCCESS) return firstResult;
        }

        // resolve second clause.
        final ResolutionResult secondResult = this.secondClause.resolve(game);
        if (secondResult.getState() == SUCCESS) this.resolutionState = COMPLETE;
        return secondResult;
    }

    /**
     * Get the current state of this clause's resolution.
     * @return {@link ResolutionState}.
     */
    @Override
    @NonNull
    public ResolutionState getResolutionState() {
        return this.resolutionState;
    }

    /**
     * Reset the clause to the ready state.
     */
    @Override
    public void reset() {
        this.firstClause.reset();
        this.secondClause.reset();
        this.resolutionState = READY;
    }
}
