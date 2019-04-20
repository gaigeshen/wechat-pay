package me.gaigeshen.wechat.pay;

import org.junit.Test;

/**
 * @author gaigeshen
 */
public class RefundResultHelperTest {
  @Test
  public void testDecrypt() {
    RefundResultHelper helper = RefundResultHelper.create(
            "m4NnwrtY0jhpDgNp65H1V/0OWMtSoTYhhY89MHbflhmnaHq9ZKjx9ABq6Jpg4SccA8" +
                    "76HVy7J9P85NpdvCMNGInZ4fANDRE+YfZe4HeF+bbFj6JETcEFPpE9YW+bTbC0D+gl/o" +
                    "tScJfvB2QUK7+EeBGPHN1EWX9zbr2Gw6AUaORdFk3mGxV5dtjuwWQrv5juzkXDs33Z2dUM" +
                    "slO+i3j0cTDHqwS4hptx2j6h2HvzgzltFbjo7nysU+4rArqJvrGW/9r18e1St9XgG21NALqi" +
                    "xfaSmqetOR4zLVL4/+z3CEz8cg5r+/4GUOTf3XFmLCZ/wEkRQhKRNVibG0NFfiG3KnqArMJ/d" +
                    "heQHCd7qL+XX/ZV6tj8RLMgL7R6hOiR03Ljyikdxq9M3K9CTYgf03pHJd3geXX1LgXrLxc1flL6" +
                    "NW+zD3ZayGYpr1WpLsSMG7z8W5j1pme6cRj3n2+CwSFnOnOkxaFuLKoJAJIqM3gbC0eN++vY7" +
                    "3RKphlI4zZqg6o5s3MXI6ju1yoi/ZQ+XbTg2JttsdbU0aySernKwkt0rYMf0j/Mcvo2axgHb" +
                    "I3w/iTm141WxHUjkQ+ga2X1pOWdGifGhSmMP8oGaA/WD5MAsK1qXX0eFvUWS/PTauCSTWq5Cm" +
                    "r8loA/KL3jgvB0nyR4mfccB+tPy4Ny7kzOlr/VNeb0ULf96R0AWFWCtdt8AmujAP0DYiM5FSmY" +
                    "LI0XRhpSDjnEbBM8+isNE1GlAVR3NzzemwQORihScovpAktbRSN/d3N+NgTjSoVDiJvCOxCs3th" +
                    "X9qt9iwYbA+/X/gv8lza2FZyIzwkQxGRcYl8JWKpXzNW8EWUNVnSLdHvQttDeV3CvgP/x579R" +
                    "Gd6whyFYS6AaI0qw7oTjCFh2EHS/VzGvFuv166ZlVIJ4MNvg79O9h63ZOSE1LzVqEsVh8fDCf" +
                    "M2GgJ9aUdl95Djgunit4yIZOdoigR3f/BEHKrYCEham11rYohaAXs4XAXWihsV3WD5j4G/P+t" +
                    "xvjAwujvf4HDwzHgFsmSml013U2mUiy+v4zw==",
            "2IBtBXdrqC3kCBs4gaceL7nl2nnFadQv");
    String decrypted = helper.decrypt();

    // <root>
    // <out_refund_no><![CDATA[2531340110812300]]></out_refund_no>
    // <out_trade_no><![CDATA[2531340110812100]]></out_trade_no>
    // <refund_account><![CDATA[REFUND_SOURCE_RECHARGE_FUNDS]]></refund_account>
    // <refund_fee><![CDATA[1]]></refund_fee>
    // <refund_id><![CDATA[50000505542018011003064518841]]></refund_id>
    // <refund_recv_accout><![CDATA[支付用户零钱]]></refund_recv_accout>
    // <refund_request_source><![CDATA[API]]></refund_request_source>
    // <refund_status><![CDATA[SUCCESS]]></refund_status>
    // <settlement_refund_fee><![CDATA[1]]></settlement_refund_fee>
    // <settlement_total_fee><![CDATA[1]]></settlement_total_fee>
    // <success_time><![CDATA[2018-01-10 10:31:24]]></success_time>
    // <total_fee><![CDATA[1]]></total_fee>
    // <transaction_id><![CDATA[4200000052201801101409025381]]></transaction_id>
    // </root>
    System.out.println(decrypted);
  }
}
