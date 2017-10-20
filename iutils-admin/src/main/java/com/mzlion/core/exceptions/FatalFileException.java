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
package com.mzlion.core.exceptions;

/**
 * Thrown on an unrecoverable problem encountered in the file operate.
 *
 * @author mzlion on 2016/6/7.
 */
public class FatalFileException extends RuntimeException {

    /**
     * Create a new FatalFileException with the specified message.
     *
     * @param message the detail message
     */
    public FatalFileException(String message) {
        super(message);
    }

    /**
     * Create a new FatalFileException with the root cause.
     *
     * @param cause the root cause
     */
    public FatalFileException(Throwable cause) {
        super(cause);
    }

    /**
     * Create a new FatalFileException with the specified message and root cause.
     *
     * @param message the detail message
     * @param cause   the root cause
     */
    public FatalFileException(String message, Throwable cause) {
        super(message, cause);
    }
}
