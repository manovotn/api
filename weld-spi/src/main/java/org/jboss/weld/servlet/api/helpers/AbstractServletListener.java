/*
 * JBoss, Home of Professional Open Source
 * Copyright 2010, Red Hat, Inc., and individual contributors
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
package org.jboss.weld.servlet.api.helpers;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.http.HttpSessionEvent;

import org.jboss.weld.servlet.api.ServletListener;

/**
 * No-op implementation of ServletListener
 *
 * @author Pete Muir
 *
 */
public class AbstractServletListener implements ServletListener {

    public void contextDestroyed(ServletContextEvent sce) {
    }

    public void contextInitialized(ServletContextEvent sce) {
    }

    public void requestDestroyed(ServletRequestEvent sre) {
    }

    public void requestInitialized(ServletRequestEvent sre) {
    }

    public void sessionCreated(HttpSessionEvent se) {
    }

    public void sessionDestroyed(HttpSessionEvent se) {
    }

}
