/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2026 Red Hat, Inc., and individual contributors
 * as indicated by the @author tags.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package io.undertow.server.handlers;

import java.util.Locale;

import io.undertow.server.session.SessionCookieConfig;
import io.undertow.testutils.category.UnitTest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

/**
 * @author Sharang Gupta
 */
@Category(UnitTest.class)
public class CookieImplTestCase {

    @Test
    public void testSameSiteAttributeDoesNotDependOnDefaultLocale() {
        final Locale previous = Locale.getDefault();
        Locale.setDefault(new Locale("tr", "TR"));
        try {
            CookieImpl cookie = new CookieImpl("a", "b");
            cookie.setAttribute("SameSite", "Strict");
            Assert.assertEquals("Strict", cookie.getSameSiteMode());
            Assert.assertEquals("Strict", cookie.getAttribute("SameSite"));

            SessionCookieConfig config = new SessionCookieConfig();
            config.setAttribute("SameSite", "Strict");
            Assert.assertEquals("Strict", config.getAttribute("SameSite"));
        } finally {
            Locale.setDefault(previous);
        }
    }
}
