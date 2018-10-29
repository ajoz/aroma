package io.aroma.core;

import android.support.annotation.NonNull;

/**
 *
 * @param <A>
 */
public abstract class Result<A> {
    /**
     *
     * @param <A>
     */
    public static class Success<A> extends Result<A> {
        private final A value;

        public Success(@NonNull final A value) {
            this.value = value;
        }
    }

    /**
     *
     * @param <A>
     */
    public static class Failure<A> extends Result<A> {
        private final Throwable throwable;

        public Failure(@NonNull final Throwable throwable) {
            this.throwable = throwable;
        }
    }
}
