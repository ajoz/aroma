package io.aroma.core;

import android.support.annotation.NonNull;

/**
 *
 * @param <A>
 */
public abstract class Conversion<A> {
    /**
     *
     * @param <A>
     */
    public static class Ok<A> extends Conversion<A> {
        private final A value;

        public Ok(@NonNull final A value) {
            this.value = value;
        }
    }

    /**
     *
     * @param <A>
     */
    public static class Failed<A> extends Conversion<A> {
        private final Throwable throwable;

        public Failed(@NonNull final Throwable throwable) {
            this.throwable = throwable;
        }
    }
}
