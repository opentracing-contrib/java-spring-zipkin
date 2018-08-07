/**
 * Copyright 2018 The OpenTracing Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package io.opentracing.contrib.java.spring.zipkin.starter.sender;

import static org.awaitility.Awaitility.await;
import static org.junit.Assert.assertEquals;

import io.opentracing.contrib.java.spring.zipkin.starter.AbstractZipkinTracerSenderSpringTest;
import java.io.IOException;
import java.util.List;
import org.junit.Test;
import zipkin2.Span;
import zipkin2.junit.ZipkinRule;

public class ZipkinTracerWithDefaultSenderSpringTest extends AbstractZipkinTracerSenderSpringTest {

  @Test
  public void testIfTracerIsZipkinTracer() {
    assertSenderUrl("http://localhost:9411/api/v2/spans");
  }

  @Test
  public void testWiring() throws InterruptedException, IOException {
    ZipkinRule zipkin = new ZipkinRule();
    zipkin.start(9411);
    tracer.buildSpan("bar").start().finish();
    await().until(() -> {
      reporter.flush();
      return zipkin.getTraces().size() == 1;
    });
    List<Span> trace = zipkin.getTraces().get(0);
    assertEquals(1, trace.size());
    assertEquals("bar", trace.get(0).name());
    zipkin.shutdown();
  }

}
