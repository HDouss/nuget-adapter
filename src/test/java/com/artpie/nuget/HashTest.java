/*
 * MIT License
 *
 * Copyright (c) 2020 Artipie
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.artpie.nuget;

import com.artipie.asto.Key;
import com.artipie.asto.blocking.BlockingStorage;
import com.artipie.asto.fs.FileStorage;
import com.google.common.hash.HashCode;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for {@link Hash}.
 *
 * @since 0.1
 */
class HashTest {

    /**
     * Storage used in tests.
     */
    private BlockingStorage storage;

    @BeforeEach
    void init() throws Exception {
        this.storage = new BlockingStorage(
            new FileStorage(
                Files.createTempDirectory(HashTest.class.getName()).resolve("repo")
            )
        );
    }

    @Test
    void shouldSave() {
        final String id = "abc";
        final String version = "0.0.1";
        final Hash hash = new Hash(HashCode.fromString("0123456789abcdef"));
        hash.save(this.storage, new PackageIdentity(id, version));
        final Key.From key = new Key.From(id, version, "abc.0.0.1.nupkg.sha512");
        MatcherAssert.assertThat(
            this.storage.value(key),
            Matchers.equalTo("ASNFZ4mrze8=".getBytes(StandardCharsets.US_ASCII))
        );
    }
}
