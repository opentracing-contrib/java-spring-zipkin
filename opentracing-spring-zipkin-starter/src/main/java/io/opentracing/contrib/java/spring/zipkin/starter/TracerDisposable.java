package io.opentracing.contrib.java.spring.zipkin.starter;

import io.opentracing.Tracer;
import org.springframework.beans.factory.DisposableBean;

/**
 * For the elegant turn-off tracer
 *
 * @author : KangNing Hu
 * @since 1.0.2
 */
public class TracerDisposable implements DisposableBean {

  private final Tracer tracer;

  public TracerDisposable(Tracer tracer) {
    this.tracer = tracer;
  }

  @Override
  public void destroy() throws Exception {
    if (this.tracer != null) {
      this.tracer.close();
    }
  }
}
