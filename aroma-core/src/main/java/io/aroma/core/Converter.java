package io.aroma.core;

import android.support.annotation.NonNull;

/**
 * Describes all classes capable of changing a String token to some type. This
 * conversion can be a failing operation as each Converter returns a Conversion.
 *
 * @param <A> type of the value returned by the Converter
 */
public interface Converter<A> {
    /**
     * Performs a conversion from a String token to a desired type. This
     * operation might fail, both successful result and failure will be wrapped
     * in a {@link Conversion}.
     *
     * @param token String token parsed directly from the XML input
     * @return {@link Conversion.Ok} if the the conversion is successful,
     * {@link Conversion.Failed} otherwise.
     */
    Conversion<A> convert(@NonNull final String token);
}
