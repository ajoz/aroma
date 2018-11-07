package io.aroma.core;

import android.content.Context;
import static io.aroma.core.AromaAssert.assertMapType;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE)
public class AromaNonErrorsNoEntriesTest {
    @Test
    public void no_type_or_collection_should_return_empty_map() {
        // given:
        final Context context = RuntimeEnvironment.application.getApplicationContext();

        // when:
        final Map<String, Collection<String>> tested =
                Aroma.from(context)
                        .parse(R.xml.test_nonerror_default_attrs_no_entries);

        // then:
        assertTrue(tested.isEmpty());
        assertMapType(tested, HashMap.class);
    }

    @Test
    public void no_type_collection_is_set_should_return_empty_map() {
        // given:
        final Context context = RuntimeEnvironment.application.getApplicationContext();

        // when:
        final Map<String, Collection<String>> tested =
                Aroma.from(context)
                        .parse(R.xml.test_nonerror_collection_set_no_entries);

        // then:
        assertTrue(tested.isEmpty());
        assertMapType(tested, HashMap.class);
    }

    @Test
    public void type_is_set_no_collection_should_return_empty_map() {
        // given:
        final Context context = RuntimeEnvironment.application.getApplicationContext();

        // when:
        final Map<String, Collection<String>> tested =
                Aroma.from(context)
                        .parse(R.xml.test_nonerror_type_set_no_entries);

        // then:
        assertTrue(tested.isEmpty());
        assertMapType(tested, LinkedHashMap.class);
    }

    @Test
    public void type_is_set_collection_is_set_should_return_empty_map() {
        // given:
        final Context context = RuntimeEnvironment.application.getApplicationContext();

        // when:
        final Map<String, Collection<String>> tested =
                Aroma.from(context)
                        .parse(R.xml.test_nonerror_attrs_set_no_entries);

        // then:
        assertTrue(tested.isEmpty());
        assertMapType(tested, LinkedHashMap.class);
    }
}
