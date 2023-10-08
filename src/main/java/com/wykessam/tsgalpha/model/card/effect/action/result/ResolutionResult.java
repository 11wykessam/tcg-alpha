package com.wykessam.tsgalpha.model.card.effect.action.result;

import lombok.Builder;
import lombok.Getter;

/**
 * @author Samuel Wykes.
 * Represents the result of attempting to resolve an effect.
 */
@Getter
@Builder
public class ResolutionResult {

    private final ResolutionResultState state;

}
