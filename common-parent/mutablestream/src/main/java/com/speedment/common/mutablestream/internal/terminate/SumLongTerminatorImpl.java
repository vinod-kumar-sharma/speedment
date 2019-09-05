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
import java.util.stream.LongStream;
import com.speedment.common.mutablestream.terminate.SumLongTerminator;

/**
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public final class SumLongTerminatorImpl
extends AbstractTerminator<Long, LongStream, Long> 
implements SumLongTerminator {
    
    public SumLongTerminatorImpl(HasNext<Long, LongStream> previous, boolean parallel) {
        super(previous, parallel);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Long execute() {
        try (final LongStream stream = buildPrevious()) {
            return stream.sum();
        }
    }
}