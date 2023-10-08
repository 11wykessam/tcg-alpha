package com.wykessam.tsgalpha.model.card.effect.flow;

import com.wykessam.tsgalpha.model.card.effect.ResolutionState;
import com.wykessam.tsgalpha.model.card.effect.action.result.ResolutionResult;
import com.wykessam.tsgalpha.model.card.effect.action.result.ResolutionResultState;
import com.wykessam.tsgalpha.model.game.IGame;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.wykessam.tsgalpha.model.card.effect.ResolutionState.COMPLETE;
import static com.wykessam.tsgalpha.model.card.effect.ResolutionState.IN_PROGRESS;
import static com.wykessam.tsgalpha.model.card.effect.action.result.ResolutionResultState.PLAYER_INPUT_REQUIRED;
import static com.wykessam.tsgalpha.model.card.effect.action.result.ResolutionResultState.SUCCESS;
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

    @Mock
    private IGame game;

    @Test
    void toStringTest() {
        final DoThenClause<IFlowClause, IFlowClause> doThenClause = this.getClause();

        when(this.firstClause.toString()).thenReturn("FIRST");
        when(this.secondClause.toString()).thenReturn("SECOND");

        assertThat(doThenClause.toString()).isEqualTo(String.format(
                "DO %s THEN %s",
                "FIRST",
                "SECOND"
        ));
    }

    @Test
    void bothUnresolvedBothCompleteTest() {
        final DoThenClause<IFlowClause, IFlowClause> doThenClause = this.getClause();

        when(this.firstClause.getResolutionState()).thenReturn(IN_PROGRESS);
        when(this.firstClause.resolve(this.game)).thenReturn(this.getSuccessResult());
        when(this.secondClause.resolve(this.game)).thenReturn(this.getSuccessResult());

        final ResolutionResult result = doThenClause.resolve(this.game);

        assertThat(result.getState()).isEqualTo(SUCCESS);
        assertThat(doThenClause.getResolutionState()).isEqualTo(COMPLETE);
    }

    @Test
    void bothUnresolvedFirstIncompleteTest() {
        final DoThenClause<IFlowClause, IFlowClause> doThenClause = this.getClause();

        when(this.firstClause.getResolutionState()).thenReturn(IN_PROGRESS);
        when(this.firstClause.resolve(this.game)).thenReturn(this.getInProgressResult());

        final ResolutionResult result = doThenClause.resolve(this.game);

        assertThat(result.getState()).isEqualTo(PLAYER_INPUT_REQUIRED);
        assertThat(doThenClause.getResolutionState()).isEqualTo(IN_PROGRESS);
    }

    @Test
    void bothUnresolvedSecondIncompleteTest() {
        final DoThenClause<IFlowClause, IFlowClause> doThenClause = this.getClause();

        when(this.firstClause.getResolutionState()).thenReturn(IN_PROGRESS);
        when(this.firstClause.resolve(this.game)).thenReturn(this.getSuccessResult());
        when(this.firstClause.resolve(this.game)).thenReturn(this.getInProgressResult());

        final ResolutionResult result = doThenClause.resolve(this.game);

        assertThat(result.getState()).isEqualTo(PLAYER_INPUT_REQUIRED);
        assertThat(doThenClause.getResolutionState()).isEqualTo(IN_PROGRESS);
    }

    @Test
    void firstResolvedSecondCompleteTest() {
        final DoThenClause<IFlowClause, IFlowClause> doThenClause = this.getClause();

        when(this.firstClause.getResolutionState()).thenReturn(COMPLETE);
        when(this.secondClause.resolve(this.game)).thenReturn(this.getSuccessResult());

        final ResolutionResult result = doThenClause.resolve(this.game);

        assertThat(result.getState()).isEqualTo(SUCCESS);
        assertThat(doThenClause.getResolutionState()).isEqualTo(COMPLETE);
    }

    @Test
    void firstResolvedSecondIncompleteTest() {
        final DoThenClause<IFlowClause, IFlowClause> doThenClause = this.getClause();

        when(this.firstClause.getResolutionState()).thenReturn(COMPLETE);
        when(this.secondClause.resolve(this.game)).thenReturn(this.getInProgressResult());

        final ResolutionResult result = doThenClause.resolve(this.game);

        assertThat(result.getState()).isEqualTo(PLAYER_INPUT_REQUIRED);
        assertThat(doThenClause.getResolutionState()).isEqualTo(IN_PROGRESS);
    }

    private DoThenClause<IFlowClause, IFlowClause> getClause() {
        return DoThenClause
                .builder()
                .firstClause(this.firstClause)
                .secondClause(this.secondClause)
                .build();
    }

    private ResolutionResult getSuccessResult() {
        return ResolutionResult.builder()
                .state(SUCCESS)
                .build();
    }

    private ResolutionResult getInProgressResult() {
        return ResolutionResult.builder()
                .state(PLAYER_INPUT_REQUIRED)
                .build();
    }

}