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

import org.elypia.commandler.annotation.stereotypes.ParamAdapter;
import org.elypia.commandler.api.Adapter;
import org.elypia.commandler.event.ActionEvent;
import org.elypia.commandler.metadata.MetaParam;

import javax.inject.Inject;
import java.text.*;
import java.util.Objects;

/**
 * @author seth@elypia.org (Seth Falco)
 */
@ParamAdapter({Long.class, Integer.class, Short.class, Byte.class, Double.class, Float.class})
public class NumberAdapter implements Adapter<Number> {

    private NumberFormat defaultFormat;

    public NumberAdapter() {
        this(NumberFormat.getInstance());
    }

    @Inject
    public NumberAdapter(NumberFormat defaultFormat) {
        this.defaultFormat = defaultFormat;
    }

    @Override
    public Number adapt(String input, Class<? extends Number> type, MetaParam metaParam, ActionEvent<?, ?> event) {
        Objects.requireNonNull(input);
        Objects.requireNonNull(type);

        ParsePosition position = new ParsePosition(0);
        Number number = defaultFormat.parse(input, position);

        if (position.getErrorIndex() != -1 || input.length() != position.getIndex())
            return null;

        if (type == Double.class || type == double.class)
            return number.doubleValue();
        if (type == Float.class || type == float.class)
            return number.floatValue();

        if (type == Long.class || type == long.class)
            return number.longValue();
        if (type == Integer.class || type == int.class)
            return number.intValue();
        if (type == Short.class || type == short.class)
            return number.shortValue();
        if (type == Byte.class || type == byte.class)
            return number.byteValue();

        throw new IllegalStateException(NumberAdapter.class + " does not support the type " + type + ".");
    }

    /**
     * Calls {@link #adapt(String, Class, MetaParam)} but uses
     * the default type of {@link Integer}.
     *
     * @see #adapt(String, Class, MetaParam)
     * @param input The parameter input.
     * @return The number or null if it wasn't possible to adapt this.
     */
    @Override
    public Number adapt(String input) {
        return adapt(input, Integer.class);
    }
}
