package com.wykessam.tsgalpha.model.card.effect.condition;

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

}
