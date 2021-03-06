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

package org.elypia.commandler.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * Generic utilities for which can be used in chat.
 * Most utilities provided are required by Commandler.
 *
 * @author seth@elypia.org (Seth Falco)
 */
public final class ChatUtils {

    /** Many chat services use two regional indicators to represent a country. */
    private static final Map<String, String> regionalIndicators = Map.ofEntries(
        Map.entry("A", "\uD83C\uDDE6"),
        Map.entry("B", "\uD83C\uDDE7"),
        Map.entry("C", "\uD83C\uDDE8"),
        Map.entry("D", "\uD83C\uDDE9"),
        Map.entry("E", "\uD83C\uDDEA"),
        Map.entry("F", "\uD83C\uDDEB"),
        Map.entry("G", "\uD83C\uDDEC"),
        Map.entry("H", "\uD83C\uDDED"),
        Map.entry("I", "\uD83C\uDDEE"),
        Map.entry("J", "\uD83C\uDDEF"),
        Map.entry("K", "\uD83C\uDDF0"),
        Map.entry("L", "\uD83C\uDDF1"),
        Map.entry("M", "\uD83C\uDDF2"),
        Map.entry("N", "\uD83C\uDDF3"),
        Map.entry("O", "\uD83C\uDDF4"),
        Map.entry("P", "\uD83C\uDDF5"),
        Map.entry("Q", "\uD83C\uDDF6"),
        Map.entry("R", "\uD83C\uDDF7"),
        Map.entry("S", "\uD83C\uDDF8"),
        Map.entry("T", "\uD83C\uDDF9"),
        Map.entry("U", "\uD83C\uDDFA"),
        Map.entry("V", "\uD83C\uDDFB"),
        Map.entry("W", "\uD83C\uDDFC"),
        Map.entry("X", "\uD83C\uDDFD"),
        Map.entry("Y", "\uD83C\uDDFE"),
        Map.entry("Z", "\uD83C\uDDFF")
    );

    private ChatUtils() {
        // Do nothing
    }

    /**
     * Replace all upper case characters with regional indicators
     * characters instead.
     *
     * @param input The source string to replace from.
     * @return The new string with all upper case characters replaced.
     */
    public static String replaceWithIndicators(String input) {
        for (Map.Entry<String, String> entry : regionalIndicators.entrySet())
            input = input.replace(entry.getKey(), entry.getValue());

        return input;
    }

    public static String replaceFromIndicators(String input) {
        for (Map.Entry<String, String> entry : regionalIndicators.entrySet())
            input = input.replace(entry.getValue(), entry.getKey());

        return input;
    }

    /**
     * @param body The string body to truncate.
     * @param maxLength The maximum length the result can be.
     * @return The string itself, or a truncated version of the string.
     */
    public static String truncate(String body, int maxLength) {
        return truncateAndAppend(body, maxLength, null);
    }

    /**
     * @param body The string body to truncate.
     * @param maxLength The maximum length the result can be.
     * @param append What to append to the end of the string if truncated.
     * @return The string itself, or a truncated version of the string.
     */
    public static String truncateAndAppend(String body, int maxLength, String append) {
        if (append != null)
            maxLength -= append.length();

        String truncated = StringUtils.truncate(body, maxLength);

        if (append != null && truncated.length() != body.length())
            truncated += append;

        return truncated;
    }
}
