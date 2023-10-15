package com.wykessam.tsgalpha.model.card.effect.action;

import com.wykessam.tsgalpha.api.request.EffectResolutionRequestV1;
import com.wykessam.tsgalpha.api.response.EffectResolutionResponseV2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.test.StepVerifier;

import static com.wykessam.tsgalpha.model.card.effect.ResolutionState.RESOLVED;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Samuel Wykes.
 */
@ExtendWith(MockitoExtension.class)
class NullClauseTest {

    @Mock
    private EffectResolutionRequestV1 request;

    @Test
    void toStringTest() {
        assertThat(getClause().toString())
                .isEqualTo("NULL");
    }

    @Test
    void resolve() {
        StepVerifier.create(getClause().resolve(this.request))
                .assertNext(response -> {
                    assertThat(response).isEqualTo(EffectResolutionResponseV2.success());
                })
                .verifyComplete();
    }

    @Test
    void getResolutionState() {
        assertThat(getClause().getResolutionState())
                .isEqualTo(RESOLVED);
    }

    @Test
    void resetTest() {
        StepVerifier.create(getClause().reset())
                .verifyComplete();
    }

    private static NullClause getClause() {
        return new NullClause();
    }

}