package com.wykessam.tsgalpha.model.card.effect.flow;

import com.wykessam.tsgalpha.model.card.effect.action.IActionClause;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.constraints.Null;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

/**
 * @author Samuel Wykes.
 */
@ExtendWith(MockitoExtension.class)
class DoClauseTest {

    @Mock
    private IActionClause action;

    @Test
    void toStringTest() {
        final DoClause<IActionClause> doClause = DoClause
                .builder()
                .action(this.action)
                .build();

        when(this.action.toString()).thenReturn("ACTION");

        assertThat(doClause.toString()).isEqualTo(String.format(
                "DO %s",
                "ACTION"
        ));
    }

}