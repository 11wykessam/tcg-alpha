package com.wykessam.tsgalpha.model.card.effect.flow;

import com.wykessam.tsgalpha.model.card.effect.condition.IConditionalClause;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

/**
 * @author Samuel Wykes.
 */
@ExtendWith(MockitoExtension.class)
class IfThenElseClauseTest {

    @Mock
    private IConditionalClause condition;

    @Mock
    private IFlowClause firstClause;

    @Mock
    private IFlowClause secondClause;

    @Test
    void toStringTest() {
        final IfThenElseClause<IConditionalClause, IFlowClause, IFlowClause> ifThenElseClause = IfThenElseClause
                .builder()
                .condition(this.condition)
                .thenClause(this.firstClause)
                .elseClause(this.secondClause)
                .build();

        when(this.condition.toString()).thenReturn("CONDITION");
        when(this.firstClause.toString()).thenReturn("FIRST");
        when(this.secondClause.toString()).thenReturn("SECOND");

        assertThat(ifThenElseClause.toString()).isEqualTo(
                String.format(
                        "IF %s THEN %s ELSE %s",
                        "CONDITION",
                        "FIRST",
                        "SECOND"
                ));
    }

}