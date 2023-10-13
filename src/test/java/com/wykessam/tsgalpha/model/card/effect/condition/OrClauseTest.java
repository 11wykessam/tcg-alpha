package com.wykessam.tsgalpha.model.card.effect.condition;

import com.wykessam.tsgalpha.api.request.EffectResolutionRequestV1;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.stream.Stream;

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

    @Mock
    private EffectResolutionRequestV1 request;

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

    @ParameterizedTest
    @MethodSource("getBooleanCombinations")
    void orTest(final boolean firstResult, final boolean secondResult) {
        final OrClause<IConditionalClause, IConditionalClause> andClause = this.getClause();

        when(this.firstCondition.resolve(this.request))
                .thenReturn(Mono.just(firstResult));
        when(this.secondCondition.resolve(this.request))
                .thenReturn(Mono.just(secondResult));

        StepVerifier.create(andClause.resolve(this.request))
                .assertNext(response -> {
                    assertThat(response).isEqualTo(firstResult || secondResult);
                })
                .verifyComplete();
    }

    private OrClause<IConditionalClause, IConditionalClause> getClause() {
        return OrClause.builder()
                .firstCondition(this.firstCondition)
                .secondCondition(this.secondCondition)
                .build();
    }

    private static Stream<Arguments> getBooleanCombinations() {
        return Stream.of(
                Arguments.of(true, true),
                Arguments.of(true, false),
                Arguments.of(false, true),
                Arguments.of(false, false)
        );
    }

}