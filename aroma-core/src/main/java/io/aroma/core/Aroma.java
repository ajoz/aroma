package io.aroma.core;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.XmlRes;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

import static io.aroma.core.converters.Converters.stringConverter;

/**
 * @param <A>
 * @param <B>
 */
public final class Aroma<A, B> {
    public enum MapTypes {
        HASHMAP,
        TREEMAP,
        LINKED_HASHMAP
    }

    public enum CollectionTypes {
        LIST,
        SET,
        ORDERED_SET
    }

    @NonNull
    private final Context context;

    @NonNull
    private final Converter<A> keyConverter;
    @NonNull
    private final Converter<B> valueConverter;

    @NonNull
    private final MapTypes mapType;
    @NonNull
    private final CollectionTypes collectionType;

    private final boolean continueOnError;

    private Aroma(@NonNull final Context context,
                  @NonNull final Converter<A> keyConverter,
                  @NonNull final Converter<B> valueConverter,
                  @NonNull final MapTypes mapType,
                  @NonNull final CollectionTypes collectionType,
                  final boolean continueOnError) {
        this.context = context;
        this.keyConverter = keyConverter;
        this.valueConverter = valueConverter;
        this.mapType = mapType;
        this.collectionType = collectionType;
        this.continueOnError = continueOnError;
    }

    /**
     * Creates an instance of Aroma parser from the given Android {@link Context}.
     * Calling a {@linkplain Aroma#parse(int) parse method} immediately after
     * creating the parser with {@link Aroma#from(Context)} will cause it to use
     * default values.
     *
     * @param context android context
     * @return instance of the Aroma parser
     */
    public static Aroma<String, String> from(@NonNull final Context context) {
        return new Aroma<String, String>(
                context,
                stringConverter(),
                stringConverter(),
                MapTypes.HASHMAP,
                CollectionTypes.LIST,
                false
        );
    }

    /**
     * Creates an instance of Aroma parser that has a custom key converter set.
     * By default map keys are {@linkplain String strings}, if keys should be
     * a specific or custom type a converter needs to be set.
     *
     * @param converter converter used for key conversion
     * @param <C>       type of the converted key
     * @return instance of the Aroma parser
     */
    public <C> Aroma<C, B> withKeyConverter(@NonNull final Converter<C> converter) {
        return new Aroma<C, B>(
                context,
                converter,
                valueConverter,
                mapType,
                collectionType,
                continueOnError
        );
    }

    /**
     * Creates an instance of Aroma parser that has a custom value converter set.
     * By default map values (stored in the collection) are
     * {@linkplain String strings}, if values should be a specific or custom type
     * a converter needs to be set.
     *
     * @param converter converter used for value conversion
     * @param <C>       type of the converted value
     * @return instance of the Aroma parser
     */
    public <C> Aroma<A, C> withValueConverter(@NonNull final Converter<C> converter) {
        return new Aroma<A, C>(
                context,
                keyConverter,
                converter,
                mapType,
                collectionType,
                continueOnError
        );
    }

    /**
     * Creates an instance of Aroma parser that will produce the specified type
     * of the map. By default created map is specified in the XML resource, if
     * there is no map type defined in XML then a {@link MapTypes#HASHMAP} will
     * be used. If map type is defined in XML and in code using this method then
     * map type specified by this call will take precedence.
     *
     * @param type type of the map returned by the parser
     * @return instance of the Aroma parser
     */
    public Aroma<A, B> withMapType(@NonNull final MapTypes type) {
        return new Aroma<A, B>(
                context,
                keyConverter,
                valueConverter,
                type,
                collectionType,
                continueOnError
        );
    }

    /**
     * Creates an instance of Aroma parser that will produce the specified type
     * of the collection stored in the map. By default created collection is
     * specified in the XML resource, if there is no collection type defined in
     * XML then a {@link CollectionTypes#LIST} will be used. If collection type
     * is defined in XML and in code using this method then collection type
     * specified by this call will take precedence.
     *
     * @param type type of the collection stored in the map returned by the parser
     * @return instance of the Aroma parser
     */
    public Aroma<A, B> withCollectionType(@NonNull final CollectionTypes type) {
        return new Aroma<A, B>(
                context,
                keyConverter,
                valueConverter,
                mapType,
                type,
                continueOnError
        );
    }

    /**
     * Specifies if the created parser should continue if there are issues while
     * parsing. Parsing issues might involve key or value conversions, unknown
     * arguments, unknown attributes, missing arguments, missing attributes.
     * By default parser will not continue on any error, setting this method
     * to true will cause the parser to keep parsing despite the errors. This
     * might cause the resulting map to be missing keys, values or be not
     * complete.
     *
     * @param continueOnError if set to true then parser will try to continue
     *                        parsing despite the found issues
     * @return instance of the Aroma parser
     */
    public Aroma<A, B> continueOnError(final boolean continueOnError) {
        return new Aroma<A, B>(
                context,
                keyConverter,
                valueConverter,
                mapType,
                collectionType,
                continueOnError
        );
    }

    /**
     * Performs parsing of the given XML resource.
     *
     * @param resourceId
     * @return
     */
    public Map<A, Collection<B>> parse(@XmlRes final int resourceId) {
        return Collections.emptyMap();
    }

    private static <A, B> Map<A, Collection<B>> getMap(@NonNull final MapTypes mapType) {
        switch (mapType) {
            case LINKED_HASHMAP:
                return new LinkedHashMap<A, Collection<B>>();
            case TREEMAP:
                return new TreeMap<A, Collection<B>>();
            case HASHMAP:
            default:
                return new HashMap<A, Collection<B>>();
        }
    }

    private static <A> Collection<A> getCollection(@NonNull final CollectionTypes collectionType) {
        switch (collectionType) {
            case SET:
                return new HashSet<A>();
            case ORDERED_SET:
                return new LinkedHashSet<A>();
            case LIST:
            default:
                return new LinkedList<A>();
        }
    }
}
