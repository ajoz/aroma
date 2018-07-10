package io.aroma.core;

import android.support.annotation.NonNull;

public interface Converter<A> {
    Conversion<A> convert(@NonNull final String token);
}
