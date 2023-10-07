package com.wykessam.tsgalpha.model.card.effect.flow;

import com.wykessam.tsgalpha.model.card.effect.condition.IConditionalClause;
import lombok.Builder;
import lombok.Getter;

/**
 * @author Samuel Wykes.
 */
@Getter
@Builder
public class IfThenElseClause<T extends IConditionalClause, U extends IFlowClause, V extends IFlowClause> {

    private final T condition;

    private final U thenClause;

    private final V elseClause;

    @Override
    public String toString() {
        return String.format(
                "IF %s THEN %s ELSE %s",
                this.condition.toString(),
                this.thenClause.toString(),
                this.elseClause.toString()
        );
    }

}
