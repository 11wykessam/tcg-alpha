package com.wykessam.tsgalpha.model.card.effect.action;

import com.wykessam.tsgalpha.model.card.effect.ResolutionState;
import com.wykessam.tsgalpha.model.card.effect.action.result.ResolutionResult;
import com.wykessam.tsgalpha.model.game.IGame;

import static com.wykessam.tsgalpha.model.card.effect.ResolutionState.*;
import static com.wykessam.tsgalpha.model.card.effect.ResolutionState.READY;
import static com.wykessam.tsgalpha.model.card.effect.action.result.ResolutionResultState.SUCCESS;

/**
 * @author Samuel Wykes.
 * Represents the terminating clause in an effect (i.e does nothing).
 */
public class NullClause implements IActionClause {

    private ResolutionState resolutionState = READY;

    @Override
    public String toString() {
        return "NULL";
    }

    /**
     * Resolve the clause.
     * Do nothing to game state and mark complete.
     * @param game {@link IGame}.
     * @return {@link ResolutionResult}.
     */
    @Override
    public ResolutionResult resolve(final IGame game) {
        this.resolutionState = COMPLETE;
        return ResolutionResult.builder()
                .state(SUCCESS)
                .build();
    }

    /**
     * Get the current state of this clause's resolution.
     * @return {@link ResolutionState}.
     */
    @Override
    public ResolutionState getResolutionState() {
        return this.resolutionState;
    }

    /**
     * Reset the clause to the ready state.
     */
    @Override
    public void reset() {
        this.resolutionState = READY;
    }
}
