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
package com.mzlion.core.io.resource;

import com.mzlion.core.io.ResourceUtils;

/**
 * <p>
 * 资源描述加载接口
 * </p>
 *
 * @author mzlion on 2016/5/6.
 */
public interface ResourceDescriptorLoader {

    String CLASSPATH_URL_PREFIX = ResourceUtils.CLASSPATH_URL_PREFIX;

    ResourceDescriptor getResourceDescriptor(String location);

    ClassLoader getClassLoader();

}
