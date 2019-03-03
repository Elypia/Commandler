package com.elypia.commandler.test.impl.modules;

import com.elypia.commandler.*;
import com.elypia.commandler.annotations.Module;
import com.elypia.commandler.annotations.*;

/**
 * This is just a really basic test module with the bare minimum
 * when it comes to testing {@link Commandler}.
 */
@Module(name = "Miscellaneous", aliases = "misc", help = "Test generic functionality and if it works.")
public class MiscModule extends Handler<String, String> {

    @Command(name = "Say", aliases = "say", help = "I'll repeat something you say.")
    public String say(
        @Param(name = "input", value = "What you want me to say.") String input
    ) {
        return input;
    }

    @Command(name = "Repeat", aliases = "repeat", help = "Repeat some text multiple times.")
    public String repeat(
        @Param(name = "input", value = "What you want me to say.") String input,
        @Param(name = "count", value = "The number of times I should say it.") int count
    ) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < count; i++)
            builder.append(input);

        return builder.toString();
    }

    @Static
    @Command(name = "Ping!", aliases = "ping", help = "Check if I am alive.")
    public String ping() {
        return "pong!";
    }
}
