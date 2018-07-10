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
     *
     * @return
     */
    public static Converter<String> stringConverter() {
        return new Converter<String>() {
            @Override
            public Conversion<String> convert(@NonNull String token) {
                return new Conversion.Ok<String>(token);
            }
        };
    }
}
