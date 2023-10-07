package com.wykessam.tsgalpha.model.card.effect.flow;

import com.wykessam.tsgalpha.model.card.effect.action.IActionClause;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import javax.validation.constraints.NotNull;

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

    @Override
    public String toString() {
        return String.format(
                "DO %s THEN %s",
                this.firstClause,
                this.secondClause
        );
    }

}
