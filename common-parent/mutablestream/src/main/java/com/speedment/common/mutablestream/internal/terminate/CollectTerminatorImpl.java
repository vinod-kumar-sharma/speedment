/*
 *
 * Copyright (c) 2006-2019, Speedment, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); You may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.speedment.common.mutablestream.internal.terminate;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.terminate.CollectTerminator;
import java.util.stream.Collector;
import java.util.stream.Stream;
import static java.util.Objects.requireNonNull;

/**
 *
 * @param <T>  the terminated stream type
 * @param <A>  the intermediary collector type
 * @param <R>  the resulting type
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public final class CollectTerminatorImpl<T, A, R> 
extends AbstractTerminator<T, Stream<T>, R>
implements CollectTerminator<T, A, R> {
    
    private final Collector<T, A, R> collector;

    public CollectTerminatorImpl(HasNext<T, Stream<T>> previous, boolean parallel, Collector<T, A, R> collector) {
        super(previous, parallel);
        this.collector = requireNonNull(collector);
    }
    
    @Override
    public Collector<T, A, R> getCollector() {
        return collector;
    }

    @Override
    public R execute() {
        try (final Stream<T> stream = buildPrevious()) {
            return stream.collect(collector);
        }
    }
}