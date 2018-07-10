package io.aroma.core;

import android.support.annotation.NonNull;

public abstract class Conversion<A> {
    public static class Ok<A> extends Conversion<A> {
        private final A value;

        public Ok(@NonNull final A value) {
            this.value = value;
        }
    }

    public static class Failed<A> extends Conversion<A> {

    }
}
