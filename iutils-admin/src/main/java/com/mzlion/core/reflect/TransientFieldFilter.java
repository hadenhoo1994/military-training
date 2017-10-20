/*
 * Copyright (C) 2016-2017 mzlion(mzllon@qq.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mzlion.core.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * Transient的field过滤器实现
 *
 * @author mzlion on 2017-05-02
 */
public class TransientFieldFilter implements FieldFilter {

    /**
     * 判断field是否需要过滤
     *
     * @param field 被检查的field
     * @return 如果为{@code true}则会过滤
     */
    @Override
    public boolean matches(Field field) {
        return Modifier.isTransient(field.getModifiers());
    }

}
