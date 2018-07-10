package io.aroma.core.converters;

import android.support.annotation.NonNull;

import io.aroma.core.Conversion;
import io.aroma.core.Converter;

/**
 * A set of general purpose {@link Converter} implementations.
 */
public final class Converters {
    private Converters() {
        throw new IllegalStateException("Converters cannot be instantiated!");
    }

    /**
     * Returns a basic converter that takes a String token and converts it
     * to a String.
     *
     * @return converter from String token to String
     */
    public static Converter<String> stringConverter() {
        return new Converter<String>() {
            @Override
            public Conversion<String> convert(@NonNull final String token) {
                return new Conversion.Ok<String>(token);
            }
        };
    }

    /**
     * Returns a basic converter that takes a String token and converts it to
     * an Integer.
     *
     * @return converter from String token to Integer
     */
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

    /**
     * Returns a basic converter that takes a String token and converts it to
     * a Double.
     *
     * @return converter from String token to Double
     */
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

    /**
     * Returns a basic converter that takes a String token and converts it to
     * a Short.
     *
     * @return converter from String token to Short
     */
    public static Converter<Short> shortConverter() {
        return new Converter<Short>() {
            @Override
            public Conversion<Short> convert(@NonNull final String token) {
                try {
                    return new Conversion.Ok<Short>(Short.parseShort(token));
                } catch (final Throwable throwable) {
                    return new Conversion.Failed<Short>(throwable);
                }
            }
        };
    }

    /**
     * Returns a basic converter that takes a String token and converts it to
     * a Float.
     *
     * @return converter from String token to Float
     */
    public static Converter<Float> floatConverter() {
        return new Converter<Float>() {
            @Override
            public Conversion<Float> convert(@NonNull final String token) {
                try {
                    return new Conversion.Ok<Float>(Float.parseFloat(token));
                } catch (final Throwable throwable) {
                    return new Conversion.Failed<Float>(throwable);
                }
            }
        };
    }

    /**
     * Returns a basic converter that takes a String token and converts it to
     * a Boolean.
     *
     * @return converter from String token to Boolean
     */
    public static Converter<Boolean> booleanConverter() {
        return new Converter<Boolean>() {
            @Override
            public Conversion<Boolean> convert(@NonNull final String token) {
                try {
                    return new Conversion.Ok<Boolean>(Boolean.parseBoolean(token));
                } catch (final Throwable throwable) {
                    return new Conversion.Failed<Boolean>(throwable);
                }
            }
        };
    }
}
