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

import org.elypia.commandler.injection.InjectorService;

import javax.validation.*;

/**
 * @author seth@elypia.org (Syed Shah)
 */
public class InjectableConstraintValidatorFactory implements ConstraintValidatorFactory {

    /** The Commandler injection service to inject dependencies. */
    private InjectorService injectorService;

    public InjectableConstraintValidatorFactory(InjectorService injectorService) {
        this.injectorService = injectorService;
    }

    @Override
    public <T extends ConstraintValidator<?, ?>> T getInstance(Class<T> key) {
        return injectorService.getInstance(key);
    }

    @Override
    public void releaseInstance(ConstraintValidator<?, ?> instance) {
        // Do nothing
    }
}
