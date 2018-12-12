package com.elypia.commandler;

import com.elypia.commandler.annotations.Module;
import com.elypia.commandler.annotations.*;
import com.elypia.commandler.interfaces.ICommandEvent;
import com.elypia.commandler.metadata.*;

import java.util.*;

public abstract class Handler<C, E, M> {

	protected Commandler<C, E, M> commandler;

	protected ModuleData module;

	protected C client;

	/**
	 * If this module is enabled or out of service.
	 */
	protected boolean enabled;

	/**
	 * Initialise the module, this will assign the values
	 * in the module and create a {@link ModuleData} which is
	 * what {@link Commandler} uses in runtime to identify modules,
	 * commands or obtain any static data.
	 *
	 * @param commandler Our parent Commandler class.
	 * @return Returns if the {@link #test()} for this module passed.
	 */
	public boolean init(Commandler<C, E, M> commandler) {
		this.commandler = commandler;
		client = commandler.getClient();
		module = commandler.getContext().getModule(this.getClass());
		enabled = test();

		return enabled;
	}

	/**
	 * Performs relevent tests to ensure this module
	 * is still working as intended. Should any test fail we will
	 * still load and display {@link #help(ICommandEvent)} for the module
	 * however all other commands will not be possible.
	 *
	 * @return If the module should remain enabled.
	 */
	public boolean test() {
		return true;
	}

	/**
	 * The default help command for a {@link Handler},
	 * this should use the {@link ModuleData} around
	 * this {@link Handler} to display helpful information
	 * to the user.
	 *
	 * @param event The {@link ICommandEvent event} produced by Commandler.
	 * @return The message to send to the end user.
	 */
	@Command(id = "Help", aliases = "help")
	public Object help(ICommandEvent<C, E, M> event) {
		StringBuilder builder = new StringBuilder();

		Module annotation = getModule().getAnnotation();
		builder.append(annotation.id());

		StringJoiner commandAliasJoiner = new StringJoiner(", ");

		for (String alias : annotation.aliases())
			commandAliasJoiner.add(alias);

		builder.append(" (" + commandAliasJoiner.toString() + ")");
		builder.append("\n" + annotation.help());

		if (!isEnabled())
			builder.append("\n" + getCommandler().getMisuseHandler().onModuleDisabled(event));

		builder.append("\n\n");

		Iterator<CommandData> metaCommandIt = getModule().getPublicCommands().iterator();

		while (metaCommandIt.hasNext()) {
			CommandData commandData = metaCommandIt.next();
			Command command = commandData.getAnnotation();
			builder.append(command.id());

			StringJoiner aliasJoiner = new StringJoiner(", ");

			for (String string : command.aliases())
				aliasJoiner.add(string);

			builder.append(" (" + aliasJoiner.toString() + ")");
			builder.append("\n" + command.help());

			List<ParamData> paramData = commandData.getInputParams();

			paramData.forEach(metaParam -> {
				Param param = metaParam.getAnnotation();
				builder.append("\n" + param.id() + ": ");
				builder.append(commandler.getEngine().getScript(event.getSource(), param.help(), Map.of()));
			});

			if (metaCommandIt.hasNext())
				builder.append("\n\n");
		}

		String helpUrl = commandler.getWebsite();

		if (helpUrl != null)
			builder.append(helpUrl);

		return builder.toString();
	}

	public C getClient() {
		return client;
	}

	public Commandler<C, E, M> getCommandler() {
		return commandler;
	}

	public ModuleData getModule() {
		return module;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public int compareTo(Handler<C, E, M> o) {
		return module.compareTo(o.getModule());
	}
}