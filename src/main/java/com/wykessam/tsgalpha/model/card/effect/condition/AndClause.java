package com.wykessam.tsgalpha.model.card.effect.condition;

import com.wykessam.tsgalpha.model.game.IGame;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

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
     * @param game {@link IGame}.
     * @return {@link Boolean}.
     */
    @Override
    public Boolean resolve(final IGame game) {
        return this.firstCondition.resolve(game) && this.secondCondition.resolve(game);
    }
}
