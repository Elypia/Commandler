/*
 * Copyright 2019-2020 Elypia CIC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.elypia.commandler.annotation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author seth@elypia.org (Seth Falco)
 */
public class AnnotationUtilsTest {

    @Test
    public void isEffectivelyNull() {
        assertTrue(AnnotationUtils.isEffectivelyNull(AnnotationUtils.EFFECTIVELY_NULL));
    }

    @Test
    public void isNotEffectivelyNull() {
        assertFalse(AnnotationUtils.isEffectivelyNull("Hello, world!"));
    }

    @Test
    public void ifEffectivelyNullThenNull() {
        assertNull(AnnotationUtils.ifEffectivelyNull(AnnotationUtils.EFFECTIVELY_NULL));
    }

    @Test
    public void ifEffectivelyNullThenValue() {
        assertEquals("world", AnnotationUtils.ifEffectivelyNull(AnnotationUtils.EFFECTIVELY_NULL, "world"));
    }
}
