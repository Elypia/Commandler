package com.elypia.commandler.impl;

import com.elypia.commandler.metadata.*;

import java.util.*;

/**
 * The input object created when parsing user input.
 * If the user performed a valid command all fields should be
 * populated.
 */
public class CommandInput {

    /**
     * The module alias the user referred to.
     */
    private String module;

    /**
     * The command alias the user referred to.
     */
    private String command;

    /**
     * All parameters the user specified.
     * This list is <strong>never</strong> null.
     */
    private List<List<String>> parameters;

    /**
     * The data associated with the selected module.
     */
    private ModuleData moduleData;

    /**
     * The data associated with the selected command.
     */
    private CommandData commandData;

    /**
     * The raw user input as is.
     *
     * @param module The module accessed.
     * @param command The command performed.
     * @param parameters Any parameters specified.
     */
    public CommandInput(String module, String command, List<List<String>> parameters) {
        this.module = Objects.requireNonNull(module);
        this.command = command;
        this.parameters = Objects.requireNonNull(parameters);
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public List<List<String>> getParameters() {
        return parameters;
    }

    public void setParameters(List<List<String>> parameters) {
        this.parameters = parameters;
    }

    /**
     * @return The total number of parameters.
     */
    public int getParameterCount() {
        return parameters.size();
    }

    public ModuleData getModuleData() {
        return moduleData;
    }

    /**
     * After verifying and determining the module specified, set
     * the module data and update the alias with this modules first
     * alias.
     *
     * @param moduleData The module access by the user.
     */
    public void setModuleData(ModuleData moduleData) {
        this.moduleData = moduleData;
        this.module = moduleData.getAnnotation().aliases()[0];
    }

    public CommandData getCommandData() {
        return commandData;
    }

    /**
     * After verifying and determining the command performed, set
     * the command data and update the alias with this commands first
     * alias.
     *
     * @param commandData The command performed by the user.
     */
    public void setCommandData(CommandData commandData) {
        this.commandData = commandData;
        this.command = commandData.getAnnotation().aliases()[0];
    }

    @Override
    public String toString() {
        if (parameters.isEmpty())
            return "(0) None";

        StringJoiner parameterJoiner = new StringJoiner(", ");

        for (List<String> list : parameters) {
            StringJoiner itemJoiner = new StringJoiner(", ");

            for (String string : list)
                itemJoiner.add("'" + string + "'");

            if (list.size() > 1)
                parameterJoiner.add("[" + itemJoiner.toString() + "]");
            else
                parameterJoiner.add(itemJoiner.toString());
        }

        return "(" + parameters.size() + ") " + parameterJoiner.toString();
    }
}