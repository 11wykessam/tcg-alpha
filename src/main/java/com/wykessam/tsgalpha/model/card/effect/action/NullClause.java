package com.wykessam.tsgalpha.model.card.effect.action;

import com.wykessam.tsgalpha.model.card.effect.IClause;

/**
 * @author Samuel Wykes.
 * Represents the terminating clause in an effect (i.e does nothing).
 */
public class NullClause implements IActionClause {

    @Override
    public String toString() {
        return "NULL";
    }

}
