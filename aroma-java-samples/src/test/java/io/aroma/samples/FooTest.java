package io.aroma.samples;

import org.junit.Test;

import static junit.framework.Assert.assertNotNull;

public class FooTest {

    @Test
    public void shouldNotBeNull() {
        final Foo foo = new Foo();
        assertNotNull(foo);
    }
}
