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

package org.elypia.commandler.metadata;

import java.util.Map;

/**
 * Abstract class that represents a type with documentable elements
 * such as a {@link MetaController}, {@link MetaCommand}, and {@link MetaParam}.
 *
 * @author seth@elypia.org (Seth Falco)
 */
public abstract class MetaComponent {

    /** The user-friendly name of this item. */
    protected String name;

    /** A short help description or message for what this item does. */
    protected String description;

    /** Properties that determine how this propery acts in runtime. */
    protected Map<String, MetaProperty> properties;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Map<String, MetaProperty> getProperties() {
        return properties;
    }

    /**
     * Recieve a property of this which may be prefixed by another class.
     *
     * @param type The prefix class name of the property.
     * @param key The name of the property in this class.
     * @return The property mapped under this key.
     */
    public MetaProperty getProperty(Class<?> type, String key) {
        return getProperty(type.getName() + "." + key);
    }

    /**
     * Retrieve a property by it's full key.
     *
     * @param key The name of the property to retrieve.
     * @return The property mapped under this key.
     */
    public MetaProperty getProperty(String key) {
        return properties.get(key);
    }
}
