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

package org.elypia.commandler.adapters;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.net.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author seth@elypia.org (Seth Falco)
 */
public class UrlAdapterTest {

    @ParameterizedTest
    @ValueSource(strings = {"https://elypia.com/", "https://ely.gg/"})
    public void testUrls(String url) throws MalformedURLException {
        UrlAdapter adapter = new UrlAdapter();
        assertEquals(new URL(url), adapter.adapt(url));
    }

    @Test
    public void testMalformedUrls() {
        UrlAdapter adapter = new UrlAdapter();
        assertNull(adapter.adapt("I'm an invalid URL!"));
    }
}
