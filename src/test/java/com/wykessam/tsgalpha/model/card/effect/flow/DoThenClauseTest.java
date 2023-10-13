package com.wykessam.tsgalpha.model.card.effect.flow;

import com.wykessam.tsgalpha.api.request.EffectResolutionRequestV1;
import com.wykessam.tsgalpha.api.response.EffectResolutionResponseV2;
import com.wykessam.tsgalpha.model.card.effect.ResolutionState;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static com.wykessam.tsgalpha.model.card.effect.ResolutionState.*;
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
    private EffectResolutionRequestV1 request;

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
    void resolveBothUnresolvedBothSuccessful() {
        final DoThenClause<IFlowClause, IFlowClause> doThenClause = DoThenClause.builder()
                .firstClause(this.firstClause)
                .secondClause(this.secondClause)
                .build();

        when(this.firstClause.getResolutionState())
                .thenReturn(READY);
        when(this.secondClause.getResolutionState())
                .thenReturn(READY);
        when(this.firstClause.resolve(this.request))
                .thenReturn(Mono.just(EffectResolutionResponseV2.success()));
        when(this.secondClause.resolve(this.request))
                .thenReturn(Mono.just(EffectResolutionResponseV2.success()));

        StepVerifier.create(doThenClause.resolve(this.request))
                .assertNext(response -> {
                    assertThat(response).isEqualTo(EffectResolutionResponseV2.success());
                    assertThat(doThenClause.getResolutionState()).isEqualTo(SUCCESS);
                })
                .verifyComplete();
    }

    @Test
    void resolveBothUnresolvedFirstUnsuccessful() {
        final DoThenClause<IFlowClause, IFlowClause> doThenClause = DoThenClause.builder()
                .firstClause(this.firstClause)
                .secondClause(this.secondClause)
                .build();

        final EffectResolutionResponseV2 firstResponse = EffectResolutionResponseV2.builder()
                .resolutionState(PLAYER_INPUT_REQUIRED)
                .build();

        when(this.firstClause.getResolutionState())
                .thenReturn(READY);
        when(this.firstClause.resolve(this.request))
                .thenReturn(Mono.just(firstResponse));

        StepVerifier.create(doThenClause.resolve(this.request))
                .assertNext(response -> {
                    assertThat(response).isEqualTo(firstResponse);
                    assertThat(doThenClause.getResolutionState()).isEqualTo(firstResponse.getResolutionState());
                })
                .verifyComplete();
    }

    @Test
    void resolveBothUnresolvedSecondUnsuccessful() {
        final DoThenClause<IFlowClause, IFlowClause> doThenClause = DoThenClause.builder()
                .firstClause(this.firstClause)
                .secondClause(this.secondClause)
                .build();

        final EffectResolutionResponseV2 secondResponse = EffectResolutionResponseV2.builder()
                .resolutionState(PLAYER_INPUT_REQUIRED)
                .build();

        when(this.firstClause.getResolutionState())
                .thenReturn(READY);
        when(this.secondClause.getResolutionState())
                .thenReturn(READY);
        when(this.firstClause.resolve(this.request))
                .thenReturn(Mono.just(EffectResolutionResponseV2.success()));
        when(this.secondClause.resolve(this.request))
                .thenReturn(Mono.just(secondResponse));

        StepVerifier.create(doThenClause.resolve(this.request))
                .assertNext(response -> {
                    assertThat(response).isEqualTo(secondResponse);
                    assertThat(doThenClause.getResolutionState()).isEqualTo(secondResponse.getResolutionState());
                })
                .verifyComplete();
    }

    @Test
    void resolveBothResolved() {
        final DoThenClause<IFlowClause, IFlowClause> doThenClause = DoThenClause.builder()
                .firstClause(this.firstClause)
                .secondClause(this.secondClause)
                .build();

        when(this.firstClause.getResolutionState())
                .thenReturn(SUCCESS);
        when(this.secondClause.getResolutionState())
                .thenReturn(SUCCESS);

        StepVerifier.create(doThenClause.resolve(this.request))
                .assertNext(response -> {
                    assertThat(response).isEqualTo(EffectResolutionResponseV2.success());
                    assertThat(doThenClause.getResolutionState()).isEqualTo(SUCCESS);
                })
                .verifyComplete();
    }

    private DoThenClause<IFlowClause, IFlowClause> getClause() {
        return DoThenClause
                .builder()
                .firstClause(this.firstClause)
                .secondClause(this.secondClause)
                .build();
    }

}