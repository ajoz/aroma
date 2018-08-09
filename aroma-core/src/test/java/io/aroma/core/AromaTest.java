package io.aroma.core;

import android.content.Context;

import io.aroma.core.converters.Converters;

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
                        .withKeyConverter(Converters.integerConverter())
                        .withValueConverter(Converters.doubleConverter())
                        .parse(R.xml.reference_map);

        // then:
        Assert.assertTrue(parsedMap.isEmpty());
    }
}
