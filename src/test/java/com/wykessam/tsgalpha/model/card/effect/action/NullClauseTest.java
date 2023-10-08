package com.wykessam.tsgalpha.model.card.effect.action;

import com.wykessam.tsgalpha.model.card.effect.action.result.ResolutionResult;
import com.wykessam.tsgalpha.model.game.IGame;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.constraints.Null;

import static com.wykessam.tsgalpha.model.card.effect.ResolutionState.COMPLETE;
import static com.wykessam.tsgalpha.model.card.effect.ResolutionState.READY;
import static com.wykessam.tsgalpha.model.card.effect.action.result.ResolutionResultState.SUCCESS;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Samuel Wykes.
 */
@ExtendWith(MockitoExtension.class)
class NullClauseTest {

    @Mock
    private IGame game;

    @Test
    void constructionTest() {
        final NullClause nullClause = new NullClause();

        assertThat(nullClause.getResolutionState()).isEqualTo(READY);
    }

    @Test
    void resolveTest() {
        final NullClause nullClause = new NullClause();

        final ResolutionResult result = nullClause.resolve(this.game);

        assertThat(result.getState()).isEqualTo(SUCCESS);
        assertThat(nullClause.getResolutionState()).isEqualTo(COMPLETE);
    }

    @Test
    void resetTest() {
        final NullClause nullClause = new NullClause();

        nullClause.resolve(this.game);
        nullClause.reset();

        assertThat(nullClause.getResolutionState()).isEqualTo(READY);
    }

}