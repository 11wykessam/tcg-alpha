package com.wykessam.tsgalpha.model.card.effect.condition;

import com.wykessam.tsgalpha.model.game.IGame;
import lombok.Builder;
import lombok.Getter;

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
     * @param game {@link IGame}.
     * @return {@link Boolean}.
     */
    @Override
    public Boolean resolve(IGame game) {
        return this.firstCondition.resolve(game) || this.secondCondition.resolve(game);
    }

}
