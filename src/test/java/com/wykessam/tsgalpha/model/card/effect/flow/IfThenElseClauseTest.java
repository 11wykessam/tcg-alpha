package com.wykessam.tsgalpha.model.card.effect.flow;

import com.wykessam.tsgalpha.api.request.EffectResolutionRequestV1;
import com.wykessam.tsgalpha.api.response.EffectResolutionResponseV2;
import com.wykessam.tsgalpha.model.card.effect.condition.IConditionalClause;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static com.wykessam.tsgalpha.model.card.effect.ResolutionState.RESOLVED;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/**
 * @author Samuel Wykes.
 */
@ExtendWith(MockitoExtension.class)
class IfThenElseClauseTest {

    @Mock
    private IConditionalClause condition;

    @Mock
    private IFlowClause thenClause;

    @Mock
    private IFlowClause elseClause;

    @Mock
    private EffectResolutionRequestV1 request;

    @Test
    void toStringTest() {
        final IfThenElseClause<IConditionalClause, IFlowClause, IFlowClause> ifThenElseClause = this.getClause();

        when(this.condition.toString()).thenReturn("CONDITION");
        when(this.thenClause.toString()).thenReturn("FIRST");
        when(this.elseClause.toString()).thenReturn("SECOND");

        assertThat(ifThenElseClause.toString()).isEqualTo(
                String.format(
                        "IF %s THEN %s ELSE %s",
                        "CONDITION",
                        "FIRST",
                        "SECOND"
                ));
    }

    @Test
    void resolveThen() {
        final IfThenElseClause<IConditionalClause, IFlowClause, IFlowClause> ifThenElseClause = this.getClause();

        final EffectResolutionResponseV2 thenResponse = EffectResolutionResponseV2.success();

        when(this.condition.resolve(this.request))
                .thenReturn(Mono.just(true));
        when(this.thenClause.resolve(this.request))
                .thenReturn(Mono.just(thenResponse));

        StepVerifier.create(ifThenElseClause.resolve(this.request))
                .assertNext(response -> {
                    assertThat(response.hasError()).isFalse();
                    assertThat(response).isEqualTo(EffectResolutionResponseV2.success());
                    assertThat(ifThenElseClause.getResolutionState())
                            .isEqualTo(RESOLVED);
                })
                .verifyComplete();
    }

    @Test
    void resolveElse() {
        final IfThenElseClause<IConditionalClause, IFlowClause, IFlowClause> ifThenElseClause = this.getClause();

        final EffectResolutionResponseV2 elseResponse = EffectResolutionResponseV2.success();

        when(this.condition.resolve(this.request))
                .thenReturn(Mono.just(false));
        when(this.elseClause.resolve(this.request))
                .thenReturn(Mono.just(elseResponse));

        StepVerifier.create(ifThenElseClause.resolve(this.request))
                .assertNext(response -> {
                    assertThat(response.hasError()).isFalse();
                    assertThat(response).isEqualTo(EffectResolutionResponseV2.success());
                    assertThat(ifThenElseClause.getResolutionState())
                            .isEqualTo(RESOLVED);
                })
                .verifyComplete();
    }

    @Test
    void reset() {
        when(this.thenClause.reset())
                .thenReturn(Mono.empty());
        when(this.elseClause.reset())
                .thenReturn(Mono.empty());

        StepVerifier.create(this.getClause().reset())
                .verifyComplete();
    }

    private IfThenElseClause<IConditionalClause, IFlowClause, IFlowClause> getClause() {
        return IfThenElseClause.builder()
                .condition(this.condition)
                .thenClause(this.thenClause)
                .elseClause(this.elseClause)
                .build();
    }

}