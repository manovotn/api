/*
 * JBoss, Home of Professional Open Source
 * Copyright 2016, Red Hat, Inc., and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.weld.inject;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.enterprise.inject.AmbiguousResolutionException;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.UnsatisfiedResolutionException;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.util.TypeLiteral;

/**
 * An enhanced version of {@link Instance}.
 *
 * @author Martin Kouba
 * @seeIssue WELD-2204
 * @param <T>
 */
public interface WeldInstance<T> extends Instance<T> {

    /**
     * Obtains a contextual reference handler for the bean that has the required type and required qualifiers and is eligible for injection.
     * <p>
     * Note that each invocation of this method results in a separate {@link Instance#get()} invocation.
     *
     * @return a new handler
     * @throws UnsatisfiedResolutionException
     * @throws AmbiguousResolutionException
     */
    Handler<T> getHandler();

    /**
     * Allows to iterate over contextual reference handlers for all the beans that have the required type and required qualifiers and are eligible for
     * injection.
     * <p>
     * Note that the returned {@link Iterable} is stateless and so each {@link Iterable#iterator()} produces a new set of handlers.
     *
     * @return an iterable to iterate over the handlers
     */
    Iterable<Handler<T>> handlers();

    @Override
    WeldInstance<T> select(Annotation... qualifiers);

    @Override
    <U extends T> WeldInstance<U> select(Class<U> subtype, Annotation... qualifiers);

    @Override
    <U extends T> WeldInstance<U> select(TypeLiteral<U> subtype, Annotation... qualifiers);

     /**
     * <p>
     * Obtains a child {@code Instance} for the given required type and additional required qualifiers.
     * Must be invoked on {@code Instance<T>} where T is {@link java.lang.Object}.
     * </p>
     *
     * @param <X> the required type
     * @param subtype    a {@link java.lang.reflect.Type} representing the required type
     * @param qualifiers the additional required qualifiers
     * @return the child <tt>Instance</tt>
     * @throws IllegalArgumentException if passed two instances of the same non repeating qualifier type, or an instance of an
     *                                  annotation that is not a qualifier type
     * @throws IllegalStateException    if the container is already shutdown
     * @throws IllegalStateException    if invoked on {@code Instance<T>} where T is of any other type than {@link java.lang.Object}
     */
    <X> WeldInstance<X> select(Type subtype, Annotation... qualifiers);

    /**
     * A contextual reference handler. Not suitable for sharing between threads.
     * <p>
     * Holds the contextual reference, allows to inspect the metadata of the relevant bean and also to destroy the underlying contextual instance.
     *
     * @author Martin Kouba
     *
     * @param <T>
     */
    public interface Handler<T> extends AutoCloseable {

        /**
         *
         * @return the contextual reference
         * @see Instance#get()
         */
        T get();

        /**
         *
         * @return the bean metadata
         */
        Bean<?> getBean();

        /**
         * Destroy the contextual instance.
         * <p>
         * It's a no-op if called multiple times or if the producing {@link WeldInstance} is destroyed already.
         *
         * @see Instance#destroy(Object)
         */
        void destroy();

        /**
         * Delegates to {@link #destroy()}.
         */
        @Override
        void close();

    }

}