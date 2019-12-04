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

package org.elypia.commandler.validation;

import org.elypia.commandler.api.*;
import org.elypia.commandler.event.ActionEvent;
import org.elypia.commandler.exceptions.misuse.MisuseException;

import javax.validation.*;
import java.util.*;

/**
 * An implementation of the {@link MisuseHandler}
 * that covers the {@link ViolationException}, which is
 * concrete implemntation of the {@link MisuseException}.
 *
 * This checks and addresses validation violations that
 * may occur in commands from the validation API.
 *
 * @author seth@elypia.org (Seth Falco)
 */
public class ValidationMisuseHandler implements MisuseHandler {

    @Override
    public <X extends MisuseException> Object handle(X ex) {
        if (ex instanceof ViolationException)
            return onViolation((ViolationException)ex);

        return null;
    }

    /**
     * @param ex The exception that occured.
     * @return An error String reporting all violations.
     * @throws NullPointerException if exception is null.
     */
    private String onViolation(ViolationException ex) {
        Objects.requireNonNull(ex);

        List<ConstraintViolation<Controller>> commandViolations = new ArrayList<>();
        List<ConstraintViolation<Controller>> paramViolations = new ArrayList<>();

        for (ConstraintViolation<Controller> violation : ex.getViolations()) {
            if (violation.getInvalidValue() instanceof ActionEvent)
                commandViolations.add(violation);
            else
                paramViolations.add(violation);
        }

        StringJoiner invalidType = new StringJoiner(" ");

        if (!commandViolations.isEmpty())
            invalidType.add("the command");
        if (!paramViolations.isEmpty())
            invalidType.add("a parameter");

        String format =
            "Command failed; " + invalidType.toString() + " was invalidated.\n" +
            "Module: %s\n" +
            "Command: %s\n";

        for (var violation : commandViolations)
            format += "command: " + violation.getMessage();

        if (!commandViolations.isEmpty())
            format += "\n";

        for (var violation : paramViolations) {
            Iterator<Path.Node> iter = violation.getPropertyPath().iterator();
            Path.Node last = null;

            while (iter.hasNext())
                last = iter.next();

            format += last.getName() + ": " + violation.getMessage();
        }

        String module = ex.getActionEvent().getMetaController().getName();
        String command = ex.getActionEvent().getMetaCommand().getName();

        return String.format(format, module, command);
    }
}
