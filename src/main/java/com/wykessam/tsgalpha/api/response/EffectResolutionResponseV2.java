package com.wykessam.tsgalpha.api.response;

import com.wykessam.tsgalpha.model.card.effect.ResolutionState;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

import static com.wykessam.tsgalpha.model.card.effect.ResolutionState.RESOLVED;

/**
 * @author Samuel Wykes.
 * Response resulting from attempting to resolve a card effet.
 */
@Getter
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Jacksonized
public class EffectResolutionResponseV2 extends ErrorAwareResponse {

    private final ResolutionState resolutionState;

    /**
     * Get a successful response instance.
     * @return {@link EffectResolutionResponseV2}.
     */
    public static EffectResolutionResponseV2 success() {
        return EffectResolutionResponseV2.builder()
                .resolutionState(RESOLVED)
                .build();
    }

}
