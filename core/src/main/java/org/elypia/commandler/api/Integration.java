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

package org.elypia.commandler.api;

import org.elypia.commandler.event.ActionEvent;

import java.io.Serializable;

/**
 * An integration represents an class which manages the integration
 * or implementation of Commandler with another service or platform.
 *
 * This could represent a terminal if it's a console application
 * or a form of social media such as Discord.
 *
 * @param <S> The type of source event this integration consumes. If there
 * are multiple types, specify the highest level expected to be used.
 * @param <M> The type of message this platform sends and receives.
 * @author seth@elypia.org (Seth Falco)
 */
public interface Integration<S, M> {

    /**
     * Initialize the integration.
     * This is intended for integrations that may want to spawn threads
     * or add listeners. It's safer to do this in an init method to be called
     * as with CDI these don't always work with the object is injected
     * with CDI.
     */
    default void init() {

    }

    /**
     * @return The type of object that is sent and received by this integration.
     */
    Class<M> getMessageType();

    /**
     * @param source The event this integration has received.
     * @return A unique and {@link Serializable} ID that represents this action.
     * This can also be a generated ID if the integration does not provide
     * unique IDs itself.
     */
    Serializable getActionId(S source);

    /**
     * A utility to respond to an event.
     * This is useful for cases where it's desireable to send a
     * message in chat in a context outside of the return type of a method.
     *
     * For example returning a method in a seperate handler or listener,
     * or in a thread that's created in a command.
     *
     * @param event The event that we're responding to.
     * @param message The message to send.
     */
    void send(ActionEvent<S, M> event, M message);
}
