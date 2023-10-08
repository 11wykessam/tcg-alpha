package com.wykessam.tsgalpha.model.card.effect.flow;

import com.wykessam.tsgalpha.model.card.effect.ResolutionState;
import com.wykessam.tsgalpha.model.card.effect.action.IActionClause;
import com.wykessam.tsgalpha.model.card.effect.action.result.ResolutionResult;
import com.wykessam.tsgalpha.model.game.IGame;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;

import static com.wykessam.tsgalpha.model.card.effect.ResolutionState.IN_PROGRESS;
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
     * Resolve the clause.
     * Resolve the action inside the clause.
     * @param game {@link IGame}.
     * @return {@link ResolutionResult}.
     */
    @Override
    public ResolutionResult resolve(final IGame game) {
        this.resolutionState = IN_PROGRESS;
        final ResolutionResult result = this.action.resolve(game);
        this.resolutionState = this.action.getResolutionState();
        return result;
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
        this.action.reset();
        this.resolutionState = READY;
    }
}
