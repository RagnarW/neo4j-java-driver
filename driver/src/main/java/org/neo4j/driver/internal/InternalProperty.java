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

import java.util.Objects;

import org.neo4j.driver.v1.Function;
import org.neo4j.driver.v1.Property;

public class InternalProperty<V> implements Property<V>
{
    private final String key;
    private final V value;

    protected InternalProperty( String key, V value )
    {
        if ( key == null )
        {
            throw new IllegalArgumentException( "null key" );
        }
        if ( value == null )
        {
            throw new IllegalArgumentException( "null value" );
        }
        this.key = key;
        this.value = value;
    }

    public String key()
    {
        return key;
    }

    public V value()
    {
        return value;
    }

    public static <V> Property<V> of( String key, V value )
    {
        return new InternalProperty<>( key, value );
    }

    @Override
    public String toString()
    {
        return String.format( "%s: %s", key, Objects.toString( value ) );
    }

    public String toString( Function<V, String> printValue )
    {
        return String.format( "%s: %s", key, printValue.apply( value ) );
    }

    @Override
    public boolean equals( Object o )
    {
        if ( this == o )
        {
            return true;
        }
        if ( o == null || getClass() != o.getClass() )
        {
            return false;
        }

        InternalProperty<?> that = (InternalProperty<?>) o;

        return key.equals( that.key ) && value.equals( that.value );
    }

    @Override
    public int hashCode()
    {
        int result = key.hashCode();
        result = 31 * result + value.hashCode();
        return result;
    }
}
