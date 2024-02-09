package com.wykessam.tsgalpha.persistence.entity;

import lombok.Getter;

/**
 * @param <T> The type of the attribute being stored.
 * @author Samuel Wykes.
 * Represents an attribute in the game which can be altered but remembers its original state.
 */
@Getter
public class MutableAttribute<T> {

    private final T originalValue;
    private final T currentValue;

    private MutableAttribute(final T originalValue, final T currentValue) {
        this.originalValue = originalValue;
        this.currentValue = currentValue;
    }

    /**
     * Construct a mutable attribute.
     *
     * @param originalValue {@link U}.
     * @param currentValue  {@link U}.
     * @param <U>           The type of object contained in attribute.
     * @return {@link MutableAttribute<U>}.
     */
    public static <U> MutableAttribute<U> of(final U originalValue, final U currentValue) {
        return new MutableAttribute<>(originalValue, currentValue);
    }

    /**
     * Construct a mutable attribute.
     *
     * @param originalValue {@link U}.
     * @param <U>           The type of object contained in attribute.
     * @return {@link MutableAttribute<U>}.
     */
    public static <U> MutableAttribute<U> of(final U originalValue) {
        return new MutableAttribute<>(originalValue, originalValue);
    }

    /**
     * Create a new, mutated version of a mutable attribute.
     *
     * @param mutableAttribute {@link MutableAttribute<U>}.
     * @param currentValue     {@link U}.
     * @param <U>              The type of object contained in the attribute.
     * @return {@link MutableAttribute<U>}.
     */
    public static <U> MutableAttribute<U> mutate(final MutableAttribute<U> mutableAttribute, final U currentValue) {
        return MutableAttribute.of(mutableAttribute.getOriginalValue(), currentValue);
    }

}
