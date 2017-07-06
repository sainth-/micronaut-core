/*
 * Copyright 2017 original authors
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 */
package org.particleframework.core.io.service;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Collections;

/**
 *
 * <p>Utility class for generated META-INF/services files</p>
 *
 * @author Graeme Rocher
 * @since 1.0
 */
public class ServiceDescriptorGenerator {

    public static final String META_INF_SERVICES = "META-INF/services";

    public void generate(File targetDirectory, String className, Class serviceType) throws IOException {
        File servicesDir = new File(targetDirectory, META_INF_SERVICES);
        servicesDir.mkdirs();

        File descriptor = new File(servicesDir, serviceType.getName());
        Charset charset = StandardCharsets.UTF_8;
        Path filePath = descriptor.toPath();
        if (descriptor.exists()) {
            String contents = new String(Files.readAllBytes(filePath));
            String[] entries = contents.split("\\n");
            if (!Arrays.asList(entries).contains(className)) {
                Files.write(filePath, Collections.singletonList(className), charset, StandardOpenOption.APPEND );
            }
        } else {
            Files.write(filePath, Collections.singletonList(className), charset, StandardOpenOption.CREATE );
        }
    }
}
