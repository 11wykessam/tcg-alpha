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
public class DoClause<T extends IActionClause> implements IFlowClause {

    @NotNull
    @NonNull
    private final T action;

    @Override
    public String toString() {
        return String.format(
                "DO %s",
                this.action
        );
    }

}
