/*
 * Copyright 2019-2019 Elypia CIC
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

package org.elypia.commandler.doc;

import com.google.gson.*;
import org.elypia.commandler.Context;
import org.elypia.commandler.doc.deserializers.*;
import org.elypia.commandler.metadata.*;
import org.slf4j.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * A class to manage exportable metadata. This includes
 * actual command data for the command handler, and possibly metadata
 * for the application, or configuration for a service to work with.
 *
 * This will only export public metadata. For example where
 * {@link MetaController#isPublic()} and {@link MetaCommand#isPublic} is true.
 *
 * @author seth@elypia.org (Syed Shah)
 */
public class CommandlerDoc {

    private static final Logger logger = LoggerFactory.getLogger(CommandlerDoc.class);

    private static final Gson gson = new GsonBuilder()
        .registerTypeAdapter(MetaController.class, new MetaModuleSerializer())
        .registerTypeAdapter(MetaCommand.class, new MetaControlSerializer())
        .registerTypeAdapter(MetaParam.class, new MetaParamSerializer())
        .create();

    private List<MetaController> modules;

    public CommandlerDoc(Context context) {
        this(context.getMetaControllers());
    }

    public CommandlerDoc(MetaController... modules) {
        this(List.of(modules));
    }

    public CommandlerDoc(List<MetaController> modules) {
        this.modules = modules.stream()
            .filter(MetaController::isPublic)
            .sorted()
            .collect(Collectors.toUnmodifiableList());
    }

    public String toJson() {
        String json = gson.toJson(modules);
        logger.info("Exported JSON: {}", json);
        return json;
    }
}
