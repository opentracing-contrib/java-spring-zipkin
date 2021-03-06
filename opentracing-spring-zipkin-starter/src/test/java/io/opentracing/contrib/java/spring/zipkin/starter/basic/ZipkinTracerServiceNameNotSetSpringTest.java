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

package io.opentracing.contrib.java.spring.zipkin.starter.basic;

import static org.assertj.core.api.Java6Assertions.assertThat;

import io.opentracing.contrib.java.spring.zipkin.starter.AbstractZipkinTracerServiceNameTest;
import org.junit.Test;
import org.springframework.test.context.TestPropertySource;

@TestPropertySource(
    properties = {
        "spring.main.banner-mode=off",
        "opentracing.zipkin.enabled=true"
    }
)
public class ZipkinTracerServiceNameNotSetSpringTest extends AbstractZipkinTracerServiceNameTest {

  @Test
  public void testNameIsAsExpected() {
    assertThat(tracer).isNotNull();
    assertThat(tracer).isInstanceOf(brave.opentracing.BraveTracer.class);

    assertServiceName("unknown-spring-boot");
  }
}
