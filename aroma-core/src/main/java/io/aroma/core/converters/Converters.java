package io.aroma.core.converters;

import android.support.annotation.NonNull;

import io.aroma.core.Conversion;
import io.aroma.core.Converter;

/**
 *
 */
public final class Converters {
    private Converters() {
        throw new IllegalStateException("Converters cannot be instantiated!");
    }

    /**
     * @return
     */
    public static Converter<String> stringConverter() {
        return new Converter<String>() {
            @Override
            public Conversion<String> convert(@NonNull final String token) {
                return new Conversion.Ok<String>(token);
            }
        };
    }

    public static Converter<Integer> integerConverter() {
        return new Converter<Integer>() {
            @Override
            public Conversion<Integer> convert(@NonNull final String token) {
                try {
                    return new Conversion.Ok<Integer>(Integer.parseInt(token));
                } catch (final Throwable throwable) {
                    return new Conversion.Failed<Integer>(throwable);
                }
            }
        };
    }

    public static Converter<Double> doubleConverter() {
        return new Converter<Double>() {
            @Override
            public Conversion<Double> convert(@NonNull String token) {
                try {
                    return new Conversion.Ok<Double>(Double.parseDouble(token));
                } catch (final Throwable throwable) {
                    return new Conversion.Failed<Double>(throwable);
                }
            }
        };
    }
}
