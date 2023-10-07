package com.wykessam.tsgalpha.model.card.effect.condition;

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

}
