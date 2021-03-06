/**
 * Copyright (c) 2002-2015 "Neo Technology,"
 * Network Engine for Objects in Lund AB [http://neotechnology.com]
 *
 * This file is part of Neo4j.
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
package org.neo4j.driver.internal;

import java.util.List;

import org.neo4j.driver.internal.util.Extract;
import org.neo4j.driver.v1.Field;
import org.neo4j.driver.v1.Function;
import org.neo4j.driver.v1.RecordAccessor;
import org.neo4j.driver.v1.Value;

import static org.neo4j.driver.v1.Values.valueAsIs;

public abstract class InternalRecordAccessor implements RecordAccessor
{
    @Override
    public int fieldCount()
    {
        return keys().size();
    }

    @Override
    public Field field( String key )
    {
        int index = index( key );
        return InternalField.of( key, index, value( index ) );
    }

    @Override
    public Field field( int index )
    {
        return InternalField.of( key( index ), index, value( index) );
    }

    @Override
    public List<Field<Value>> fields()
    {
        return fields( valueAsIs() );
    }

    @Override
    public <V> List<Field<V>> fields( final Function<Value, V> mapFunction )
    {
        return Extract.fields( this, mapFunction );
    }
}
