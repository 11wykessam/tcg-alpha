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
class AndClauseTest {

    @Mock
    private IConditionalClause firstCondition;

    @Mock
    private IConditionalClause secondCondition;

    @Test
    void toStringTest() {
        final AndClause<IConditionalClause, IConditionalClause> andClause = AndClause
                .builder()
                .firstCondition(this.firstCondition)
                .secondCondition(this.secondCondition)
                .build();

        when(this.firstCondition.toString()).thenReturn("FIRST");
        when(this.secondCondition.toString()).thenReturn("SECOND");

        assertThat(andClause.toString()).isEqualTo(
                String.format(
                        "%s AND %s",
                        "FIRST",
                        "SECOND"
                ));
    }

}