package io.aroma.core;

import android.content.Context;
import static edu.emory.mathcs.backport.java.util.Collections.singleton;
import static io.aroma.core.AromaAssert.assertCollectionType;
import static io.aroma.core.AromaAssert.assertMapType;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE)
public class AromaNonErrorsSingleEntryTest {

    /*
    X ---- map tag exists, type is set, there is one entry
    X ---- map tag exists, collection is set, there is one entry
    X ---- map tag exists, both attrs set, there is one entry
    */

    @Test
    public void no_type_or_collection_should_return_single_element_multimap() {
        // given:
        final Context context = RuntimeEnvironment.application.getApplicationContext();

        // when:
        final Map<String, Collection<String>> tested =
                Aroma.from(context)
                        .parse(R.xml.test_nonerror_default_attrs_single_entry);

        // then:
        assertMapType(tested, HashMap.class);
        assertCollectionType(tested, LinkedList.class);
        assertEquals(singleton("bar"), tested.get("foo"));
    }

}
