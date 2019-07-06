package com.elypia.commandler.exceptions;

import com.elypia.commandler.Input;
import com.elypia.commandler.metadata.MetaParam;

import java.util.Objects;

public class ParamParseException extends ParamException {

    /** The particular <strong>item</strong> that failed to adapt.*/
    private String item;

    public ParamParseException(Input input, MetaParam param, String item) {
        super(input, param);
        this.item = Objects.requireNonNull(item);
    }

    public String getItem() {
        return item;
    }
}
