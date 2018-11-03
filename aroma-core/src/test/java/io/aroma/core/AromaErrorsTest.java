package io.aroma.core;

import android.content.Context;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE)
public class AromaErrorsTest {
    @Test
    public void map_tag_is_missing() {
        //TODO: Should this case be recoverable with continueOnError?

        // given:
        final Context context = RuntimeEnvironment.application.getApplicationContext();

        // when:
        final Map<String, Collection<String>> parsedMap =
                Aroma.from(context).parse(R.xml.test_error_map_tag_missing);

        // then:
        assertTrue(parsedMap.isEmpty());
        assertTrue(parsedMap instanceof HashMap);
    }

    @Test
    public void map_tag_is_not_a_root_tag() {
        //TODO: Should this case be recoverable with continueOnError?

        // given:
        final Context context = RuntimeEnvironment.application.getApplicationContext();

        // when:
        final Map<String, Collection<String>> parsedMap =
                Aroma.from(context).parse(R.xml.test_error_map_tag_not_root_tag);

        // then:
        assertTrue(parsedMap.isEmpty());
        assertTrue(parsedMap instanceof HashMap);
    }

    @Test
    public void map_tag_has_unknown_attributes() {
        // given:
        final Context context = RuntimeEnvironment.application.getApplicationContext();

        // when:
        final Map<String, Collection<String>> parsedMap =
                Aroma.from(context)
                        .parse(R.xml.test_error_map_tag_unknown_attrs);

        // then:
        assertTrue(parsedMap.isEmpty());
        assertTrue(parsedMap instanceof LinkedHashMap);
    }

    @Test
    public void map_tag_has_unknown_attributes_continue_on_error() {
        // given:
        final Context context = RuntimeEnvironment.application.getApplicationContext();

        // when:
        final Map<String, Collection<String>> parsedMap =
                Aroma.from(context)
                        .continueOnError(true)
                        .parse(R.xml.test_error_map_tag_unknown_attrs);

        // then:
        assertFalse(parsedMap.isEmpty());
        assertTrue(parsedMap instanceof LinkedHashMap);
        assertTrue(parsedMap.values() instanceof LinkedHashSet);
        assertEquals(1, parsedMap.size());
        assertEquals(2, parsedMap.get("map_tag_with").size());
        assertEquals(asList("unknown", "attributes"), parsedMap.get("map_tag_with"));
    }

    @Test
    public void map_tag_has_unknown_child_tags() {
        // given:
        final Context context = RuntimeEnvironment.application.getApplicationContext();

        // when:
        final Map<String, Collection<String>> parsedMap =
                Aroma.from(context)
                        .parse(R.xml.test_error_map_tag_unknown_child_tags);

        // then:
        assertTrue(parsedMap.isEmpty());
        assertTrue(parsedMap instanceof LinkedHashMap);
    }

    @Test
    public void map_tag_has_unknown_child_tags_continue_on_error() {
        // given:
        final Context context = RuntimeEnvironment.application.getApplicationContext();

        // when:
        final Map<String, Collection<String>> parsedMap =
                Aroma.from(context)
                        .continueOnError(true)
                        .parse(R.xml.test_error_map_tag_unknown_child_tags);

        // then:
        assertFalse(parsedMap.isEmpty());
        assertTrue(parsedMap instanceof LinkedHashMap);
        assertTrue(parsedMap.values() instanceof LinkedHashSet);
        assertEquals(1, parsedMap.size());
        assertEquals(2, parsedMap.get("map_with").size());
        assertEquals(asList("unknown", "tags"), parsedMap.get("map_with"));
    }

    @Test
    public void map_tag_has_entry_tags_with_unknown_attributes() {
        // given:
        final Context context = RuntimeEnvironment.application.getApplicationContext();

        // when:
        final Map<String, Collection<String>> parsedMap =
                Aroma.from(context)
                        .parse(R.xml.test_error_map_tag_unknown_entry_tag_attrs);

        // then:
        assertTrue(parsedMap.isEmpty());
        assertTrue(parsedMap instanceof LinkedHashMap);
    }

    @Test
    public void map_tag_has_entry_tags_with_unknown_attributes_continue_on_error() {
        // given:
        final Context context = RuntimeEnvironment.application.getApplicationContext();

        // when:
        final Map<String, Collection<String>> parsedMap =
                Aroma.from(context)
                        .continueOnError(true)
                        .parse(R.xml.test_error_map_tag_unknown_entry_tag_attrs);

        // then:
        assertFalse(parsedMap.isEmpty());
        assertTrue(parsedMap instanceof LinkedHashMap);
        assertTrue(parsedMap.values() instanceof LinkedHashSet);
        assertEquals(1, parsedMap.size());
        assertEquals(2, parsedMap.get("entry_with").size());
        assertEquals(asList("unknown", "attributes"), parsedMap.get("entry_with"));
    }

    @Test
    public void key_conversion_error() {
        // given:
        final Context context = RuntimeEnvironment.application.getApplicationContext();

        // when:
        final Map<Boolean, Collection<String>> parsedMap =
                Aroma.from(context)
                        .withKeyConversion(Conversions.booleanConversion())
                        .parse(R.xml.test_error_conversion_key_type);

        // then:
        assertTrue(parsedMap.isEmpty());
        assertTrue(parsedMap instanceof LinkedHashMap);
    }

    @Test
    public void key_conversion_error_continue_on_error() {
        // given:
        final Context context = RuntimeEnvironment.application.getApplicationContext();

        // when:
        final Map<Boolean, Collection<String>> parsedMap =
                Aroma.from(context)
                        .continueOnError(true)
                        .withKeyConversion(Conversions.booleanConversion())
                        .parse(R.xml.test_error_conversion_key_type);

        // then:
        assertFalse(parsedMap.isEmpty());
        assertTrue(parsedMap instanceof LinkedHashMap);
        assertTrue(parsedMap.values() instanceof LinkedHashSet);
        assertEquals(1, parsedMap.size());
        assertEquals(2, parsedMap.get(Boolean.FALSE).size());
        assertEquals(asList("key", "converted", "correctly"), parsedMap.get(Boolean.FALSE));
    }

    @Test
    public void value_conversion_error() {
        // given:
        final Context context = RuntimeEnvironment.application.getApplicationContext();

        // when:
        final Map<String, Collection<Boolean>> parsedMap =
                Aroma.from(context)
                        .withValueConversion(Conversions.booleanConversion())
                        .parse(R.xml.test_error_conversion_value_type);

        // then:
        assertTrue(parsedMap.isEmpty());
        assertTrue(parsedMap instanceof LinkedHashMap);
    }

    @Test
    public void value_conversion_error_continue_on_error() {
        // given:
        final Context context = RuntimeEnvironment.application.getApplicationContext();

        // when:
        final Map<String, Collection<Boolean>> parsedMap =
                Aroma.from(context)
                        .continueOnError(true)
                        .withValueConversion(Conversions.booleanConversion())
                        .parse(R.xml.test_error_conversion_value_type);

        // then:
        assertFalse(parsedMap.isEmpty());
        assertTrue(parsedMap instanceof LinkedHashMap);
        assertTrue(parsedMap.values() instanceof LinkedHashSet);
        assertEquals(1, parsedMap.size());
        assertEquals(2, parsedMap.get("value_conversion").size());
        assertEquals(asList(true, false), parsedMap.get("value_conversion"));
    }
}
