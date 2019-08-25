package com.elypia.commandler.exceptions;

import com.elypia.commandler.event.ActionEvent;

public class ModuleDisabledException extends ActionException {

    public ModuleDisabledException(ActionEvent<?, ?> action) {
        super(action);
    }

    public ModuleDisabledException(ActionEvent<?, ?> action, String message) {
        super(action, message);
    }

    public ModuleDisabledException(ActionEvent<?, ?> action, String message, Throwable cause) {
        super(action, message, cause);
    }
}
