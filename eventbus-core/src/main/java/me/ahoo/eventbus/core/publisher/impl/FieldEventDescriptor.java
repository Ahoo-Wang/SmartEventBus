/*
 * Copyright [2021-2021] [ahoo wang <ahoowang@qq.com> (https://github.com/Ahoo-Wang)].
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *      http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.ahoo.eventbus.core.publisher.impl;

import lombok.SneakyThrows;
import me.ahoo.eventbus.core.publisher.EventDescriptor;

import java.lang.reflect.Field;

/**
 * @author ahoo wang
 */
public class FieldEventDescriptor implements EventDescriptor {

    private final Field field;
    private final Class<?> eventClass;
    private final String eventName;

    public FieldEventDescriptor(String eventName, Field field) {
        field.setAccessible(true);
        this.field = field;
        this.eventClass = field.getType();
        this.eventName = eventName;
    }

    @Override
    public String getEventName() {
        return eventName;
    }

    public Class<?> getEventClass() {
        return eventClass;
    }

    @SneakyThrows
    @Override
    public Object getEventData(Object targetObject) {
        return field.get(targetObject);
    }
}
