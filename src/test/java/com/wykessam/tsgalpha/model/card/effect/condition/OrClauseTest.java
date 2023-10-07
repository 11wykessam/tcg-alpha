package com.wykessam.tsgalpha.model.card.effect.condition;

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
class OrClauseTest {

    @Mock
    private IConditionalClause firstCondition;

    @Mock
    private IConditionalClause secondCondition;

    @Test
    void toStringTest() {
        final OrClause<IConditionalClause, IConditionalClause> orClause = OrClause
                .builder()
                .firstCondition(this.firstCondition)
                .secondCondition(this.secondCondition)
                .build();

        when(this.firstCondition.toString()).thenReturn("FIRST");
        when(this.secondCondition.toString()).thenReturn("SECOND");

        assertThat(orClause.toString()).isEqualTo(
                String.format(
                        "%s OR %s",
                        "FIRST",
                        "SECOND"
                ));
    }

}