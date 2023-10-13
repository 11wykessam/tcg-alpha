package com.wykessam.tsgalpha.api.response;

import com.wykessam.tsgalpha.model.card.effect.ResolutionState;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

import static com.wykessam.tsgalpha.model.card.effect.ResolutionState.SUCCESS;

/**
 * @author Samuel Wykes.
 * Response resulting from attempting to resolve a card effet.
 */
@Getter
@Builder
@EqualsAndHashCode
@Jacksonized
public class EffectResolutionResponseV2 {

    private final ResolutionState resolutionState;

    /**
     * Get a successful response instance.
     * @return {@link EffectResolutionResponseV2}.
     */
    public static EffectResolutionResponseV2 success() {
        return EffectResolutionResponseV2.builder()
                .resolutionState(SUCCESS)
                .build();
    }

}
