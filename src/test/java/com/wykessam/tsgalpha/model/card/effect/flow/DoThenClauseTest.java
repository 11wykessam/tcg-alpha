package com.wykessam.tsgalpha.model.card.effect.flow;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/**
 * @author Samuel Wykes.
 */
@ExtendWith(MockitoExtension.class)
class DoThenClauseTest {

    @Mock
    private IFlowClause firstClause;

    @Mock
    private IFlowClause secondClause;

    @Test
    void toStringTest() {
        final DoThenClause<IFlowClause, IFlowClause> doThenClause = DoThenClause
                .builder()
                .firstClause(this.firstClause)
                .secondClause(this.secondClause)
                .build();

        when(this.firstClause.toString()).thenReturn("FIRST");
        when(this.secondClause.toString()).thenReturn("SECOND");

        assertThat(doThenClause.toString()).isEqualTo(String.format(
                "DO %s THEN %s",
                "FIRST",
                "SECOND"
        ));
    }

}