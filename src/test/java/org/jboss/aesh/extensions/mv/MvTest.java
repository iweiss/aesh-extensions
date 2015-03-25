/*
 * JBoss, Home of Professional Open Source
 * Copyright 2014 Red Hat Inc. and/or its affiliates and other contributors
 * as indicated by the @authors tag. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
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
package org.jboss.aesh.extensions.mv;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import org.jboss.aesh.console.Config;
import org.jboss.aesh.extensions.cat.Cat;
import org.jboss.aesh.extensions.cd.Cd;
import org.jboss.aesh.extensions.common.AeshTestCommons;
import org.jboss.aesh.extensions.mkdir.Mkdir;
import org.jboss.aesh.extensions.touch.Touch;
import org.jboss.aesh.extensions.mv.Mv;
import org.jboss.aesh.terminal.Key;
import org.junit.Before;
import org.junit.Test;

/**
 * @author <a href="mailto:00hf11@gmail.com">Helio Frota</a>
 */
public class MvTest extends AeshTestCommons {

    private Path tempDir;

    @Before
    public void before() throws IOException {
        tempDir = createTempDirectory();
    }

    @Test
    public void testMv() throws IOException, InterruptedException {

        prepare(Touch.class, Mkdir.class, Cd.class, Cat.class, Mv.class);
        String temp = tempDir.toFile().getAbsolutePath() + Config.getPathSeparator();

        pushToOutput("touch " + temp + "file01.txt");
        assertTrue(new File(temp + "file01.txt").exists());
        pushToOutput("mv -v " + temp + "file01.txt" + " " + temp + "file02.txt");
        assertTrue(new File(temp + "file02.txt").exists());

        pushToOutput("cd " + temp);
        pushToOutput("mkdir " + temp + "aesh_rocks");
        assertTrue(new File(temp + "aesh_rocks").exists());
        pushToOutput("mv -v " + temp + "aesh_rocks" + " " + temp + "aesh");
        assertTrue(new File(temp + "aesh").exists());

        finish();
    }

}
