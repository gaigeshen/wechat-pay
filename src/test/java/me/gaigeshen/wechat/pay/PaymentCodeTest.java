package me.gaigeshen.wechat.pay;

import me.gaigeshen.wechat.pay.commons.HttpClientExecutor;
import me.gaigeshen.wechat.pay.paymentcode.*;
import org.apache.http.ssl.SSLContextBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.security.KeyStore;
import java.util.Properties;

/**
 * 付款码支付
 *
 * @author gaigeshen
 */
public class PaymentCodeTest {
  private Config config;
  private RequestExecutor executor;
  @Before
  public void init() throws Exception {
    Properties props = new Properties();
    try (InputStream in = getClass().getResourceAsStream("/config.properties")) {
      props.load(in);
    }

    KeyStore keyStore = KeyStore.getInstance("JKS");
    try (InputStream in = getClass().getResourceAsStream("/apiclient_cert.p12")) {
      keyStore.load(in, props.getProperty("mchId", "").toCharArray());
    }
    this.config = Config.builder()
            .appid(props.getProperty("appid", ""))
            .mchId(props.getProperty("mchId", ""))
            .key(props.getProperty("key", ""))
            .secret(props.getProperty("secret", ""))
            .build();
    this.executor = new RequestExecutor(
            new HttpClientExecutor(2000, 2000, 3000,
                    SSLContextBuilder.create().loadKeyMaterial(keyStore, props.getProperty("mchId").toCharArray()).build()),
            config);
  }

  @Test
  public void testOrder() { // 提交付款码
    OrderRequest req = OrderRequest.builder()
            .deviceInfo("013467007045764")
            .body("This is description") // 商品描述
            .outTradeNo("1234013")
            .totalFee(1) // 订单金额
            .spbillCreateIp("115.196.74.179") // 终端地址
            .authCode("134665048834384090") // 付款码
            .build();

    OrderResponse resp = executor.execute(req);
    System.out.println(resp);
  }

  @Test
  public void testQueryOrder() { // 查询订单
    QueryRequest req = QueryRequest.builder()
            .outTradeNo("1234013")
            .build();
    QueryResponse resp = executor.execute(req);
    System.out.println(resp);
  }

  @Test
  public void testReverseOrder() { // 撤销订单，七日内的订单，超出时间请退款
    ReverseRequest req = ReverseRequest.builder()
            .outTradeNo("1234013")
            .build();
    ReverseResponse resp = executor.execute(req);
    System.out.println(resp);
  }

  @Test
  public void testRefundOrderRequest() { // 退款
    RefundRequest req = RefundRequest.builder()
            .outTradeNo("1234099")
            .outRefundNo("123456789") // 商户退款单号
            .totalFee(10) // 订单金额
            .refundFee(10) // 退款金额
            .refundDesc("测试退款") // 退款原因
            .build();
    RefundResponse resp = executor.execute(req);
    System.out.println(resp);
  }

  @Test
  public void testRefundQueryRequest() { // 查询退款
    RefundQueryRequest req = RefundQueryRequest.builder()
            .outTradeNo("1234011")
            .build();
    RefundQueryResponse resp = executor.execute(req);
    System.out.println(resp);
  }
}
