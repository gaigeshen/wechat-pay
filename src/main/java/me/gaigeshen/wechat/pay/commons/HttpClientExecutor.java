package me.gaigeshen.wechat.pay.commons;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.fluent.ContentResponseHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.AbstractResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import javax.net.ssl.SSLContext;
import java.io.Closeable;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

/**
 *
 *
 * @author gaigeshen
 */
public final class HttpClientExecutor implements Closeable {

  private PoolingHttpClientConnectionManager connMgr;

  private CloseableHttpClient client;
  private RequestConfig config;

  private SSLContext sslContext;

  /**
   * Create http client executor
   *
   * @param connectionRequestTimeout The timeout in milliseconds used when requesting a connection from the connection manager,
   *                                 A timeout value of zero is interpreted as an infinite timeout.
   *                                 A negative value is interpreted as undefined (system default). Default: {@code -1}
   * @param connectTimeout The timeout in milliseconds until a connection is established.
   *                       A timeout value of zero is interpreted as an infinite timeout.
   *                       A negative value is interpreted as undefined (system default). Default: {@code -1}
   * @param socketTimeout The socket timeout in milliseconds, which is the timeout for waiting for data or, put differently,
   *                      a maximum period inactivity between two consecutive data packets.
   *                      A negative value is interpreted as undefined (system default). Default: {@code -1}
   */
  public HttpClientExecutor(
          int connectionRequestTimeout,
          int connectTimeout,
          int socketTimeout) {
    this(connectionRequestTimeout, connectTimeout, socketTimeout, null);
  }

  /**
   * Create http client executor
   *
   * @param connectionRequestTimeout The timeout in milliseconds used when requesting a connection from the connection manager,
   *                                 A timeout value of zero is interpreted as an infinite timeout.
   *                                 A negative value is interpreted as undefined (system default). Default: {@code -1}
   * @param connectTimeout The timeout in milliseconds until a connection is established.
   *                       A timeout value of zero is interpreted as an infinite timeout.
   *                       A negative value is interpreted as undefined (system default). Default: {@code -1}
   * @param socketTimeout The socket timeout in milliseconds, which is the timeout for waiting for data or, put differently,
   *                      a maximum period inactivity between two consecutive data packets.
   *                      A negative value is interpreted as undefined (system default). Default: {@code -1}
   * @param sslContext The ssl context
   */
  public HttpClientExecutor(
          int connectionRequestTimeout,
          int connectTimeout,
          int socketTimeout,
          SSLContext sslContext) {

    this.config = RequestConfig.custom()
            .setConnectTimeout(connectTimeout)
            .setSocketTimeout(socketTimeout)
            .setConnectionRequestTimeout(connectionRequestTimeout)
            .build();

    this.sslContext = sslContext;

    initialize();
  }

  /**
   * 执行请求并返回字符串类型的结果
   *
   * @param req 请求内容
   * @param charset 字符集
   * @return 字符串结果
   */
  public String execute(HttpUriRequest req, Charset charset) {
    return execute(req, new ContentResponseHandler()).asString(charset);
  }

  /**
   * 执行请求并返回指定类型的结果
   *
   * @param req 请求内容
   * @param handler 响应处理器
   * @return 结果
   */
  public <T> T execute(HttpUriRequest req, AbstractResponseHandler<T> handler) {
    try {
      return client.execute(req, handler);
    } catch (IOException e) {
      throw new IllegalStateException(e);
    }
  }

  /**
   * 关闭并释放所有的资源
   */
  public void shutdown() throws IOException {
    try {
      client.close();
    } catch (IOException e) {
      throw e;
    }
  }

  @Override
  public void close() throws IOException {
    shutdown();
  }

  /**
   * 创建客户端
   */
  private void initialize() {
    SSLConnectionSocketFactory sslf = null;

    if (sslContext != null) {
      sslf = new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);
    } else {
      sslf = SSLConnectionSocketFactory.getSocketFactory();
    }

    Registry<ConnectionSocketFactory> sfr = RegistryBuilder.<ConnectionSocketFactory>create()
            .register("http", PlainConnectionSocketFactory.getSocketFactory())
            .register("https", sslf)
            .build();

    connMgr = new PoolingHttpClientConnectionManager(sfr);
    connMgr.setDefaultMaxPerRoute(100);
    connMgr.setMaxTotal(200);
    connMgr.setValidateAfterInactivity(1000);

    client = HttpClients.custom()
            .setConnectionManager(connMgr)
            .evictExpiredConnections()
            .evictIdleConnections(1800, TimeUnit.SECONDS)
            .setDefaultRequestConfig(config)
            .build();
  }
}
