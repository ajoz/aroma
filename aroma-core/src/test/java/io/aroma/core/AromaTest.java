package io.aroma.core;

import android.content.Context;

import io.aroma.core.converters.Conversions;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.Collection;
import java.util.Map;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE)
public class AromaTest {
    /*
    Tests:
    -- Non errors:
    X ---- map tag exists, there are zero entries -> Empty map, default type
    X ---- map tag exists, there is one entry -> Singleton map, default type, default collection
    X ---- map tag exists, there are multiple entries -> Full map, default type, default collection
    X ---- map tag exists, type is set, there are zero entries ->
    X ---- map tag exists, type is set, there is one entry
    X ---- map tag exists, type is set, there are multiple entries
    X ---- map tag exists, collection is set, there are zero entries
    X ---- map tag exists, collection is set, there is one entry
    X ---- map tag exists, collection is set, there are multiple entries
    X ---- map tag exists, both attrs set, there are zero entries
    X ---- map tag exists, both attrs set, there is one entry
    X ---- map tag exists, both attrs set, there are multiple entries
    -- Errors:
    X ---- missing map tag -> Empty map, default type
    X ---- misplaced map tag
    X ---- unknown map tag attributes
    X ---- unknown child tags
    ---- unknown entry tag attributes
    ---- converter error, key conversion error
    X ---- converter error, value conversion error
    */

    @Test
    public void test() {
        // given:
        final Context context = RuntimeEnvironment.application.getApplicationContext();

        // when:
        final Map<Integer, Collection<Double>> parsedMap =
                Aroma.from(context)
                        .withCollectionType(Aroma.CollectionTypes.ORDERED_SET)
                        .withMapType(Aroma.MapTypes.TREEMAP)
                        .continueOnError(true)
                        .withKeyConverter(Conversions.integerConversion())
                        .withValueConverter(Conversions.doubleConversion())
                        .parse(R.xml.reference_map);

        // then:
        Assert.assertTrue(parsedMap.isEmpty());
    }


}
