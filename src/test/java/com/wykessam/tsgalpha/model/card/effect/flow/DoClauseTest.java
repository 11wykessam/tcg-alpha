package com.wykessam.tsgalpha.model.card.effect.flow;

import com.wykessam.tsgalpha.api.request.EffectResolutionRequestV1;
import com.wykessam.tsgalpha.api.response.EffectResolutionResponseV2;
import com.wykessam.tsgalpha.model.card.effect.action.IActionClause;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static com.wykessam.tsgalpha.model.card.effect.ResolutionState.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

/**
 * @author Samuel Wykes.
 */
@ExtendWith(MockitoExtension.class)
class DoClauseTest {

    @Mock
    private IActionClause subClause;

    @Mock
    private EffectResolutionRequestV1 request;

    @Test
    void toStringTest() {
        final DoClause<IActionClause> doClause = DoClause.builder()
                .action(this.subClause)
                .build();

        when(this.subClause.toString()).thenReturn("ACTION");

        assertThat(doClause.toString()).isEqualTo(String.format(
                "DO %s",
                "ACTION"
        ));
    }

    @Test
    void subClauseSuccessful() {
        final DoClause<IActionClause> doClause = this.getClause();

        when(this.subClause.resolve(this.request))
                .thenReturn(Mono.just(EffectResolutionResponseV2.success()));

        StepVerifier.create(doClause.resolve(this.request))
                .assertNext(response -> {
                    assertThat(response.hasError()).isFalse();
                    assertThat(response).isEqualTo(EffectResolutionResponseV2.success());
                    assertThat(doClause.getResolutionState()).isEqualTo(RESOLVED);
                })
                .verifyComplete();
    }

    @Test
    void subClauseUnsuccessful() {
        final DoClause<IActionClause> doClause = this.getClause();

        final EffectResolutionResponseV2 subClauseResponse = EffectResolutionResponseV2.builder()
                .resolutionState(PLAYER_INPUT_REQUIRED)
                .build();

        when(this.subClause.resolve(this.request))
                .thenReturn(Mono.just(subClauseResponse));

        StepVerifier.create(doClause.resolve(this.request))
                .assertNext(response -> {
                    assertThat(response.hasError()).isFalse();
                    assertThat(response).isEqualTo(subClauseResponse);
                    assertThat(doClause.getResolutionState()).isEqualTo(subClauseResponse.getResolutionState());
                })
                .verifyComplete();
    }

    @Test
    void reset() {
        final DoClause<IActionClause> doClause = this.getClause();

        when(this.subClause.resolve(this.request))
                .thenReturn(Mono.just(EffectResolutionResponseV2.success()));
        when(this.subClause.reset())
                .thenReturn(Mono.empty());

        StepVerifier.create(doClause.resolve(this.request)
                .then(doClause.reset())
                .then(Mono.just(doClause.getResolutionState())))
                .assertNext(state -> {
                    assertThat(state).isEqualTo(READY);
                })
                .verifyComplete();
    }

    private DoClause<IActionClause> getClause() {
        return DoClause.builder()
                .action(this.subClause)
                .build();
    }

}