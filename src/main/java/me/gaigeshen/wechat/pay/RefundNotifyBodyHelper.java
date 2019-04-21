package me.gaigeshen.wechat.pay;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.StringReader;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.util.Base64;

/**
 * 退款通知结果帮助类
 *
 * @author gaigeshen
 */
public class RefundNotifyBodyHelper {
  private static final String ALGORITHM = "AES";
  private static final String ALGORITHM_MODE_PADDING = "AES/ECB/PKCS7Padding";

  private static final String RESULT_ENCRYPT_CONTENT_SELECT = "//req_info";
  private static final String RETURN_CODE_CONTENT_SELECT = "//return_code";
  private static final String RETURN_MSG_CONTENT_SELECT = "//return_msg";
  private static final String RETURN_CODE_SUCCESS = "SUCCESS";
  private static final String RETURN_CODE_FAIL = "FAIL";

  private final String key;

  private final Document resultDocument;
  private final Document decryptedDocument;

  private String returnCode;
  private String returnMessage;

  static {
    // Support AES/ECB/PKCS7Padding
    Security.addProvider(new BouncyCastleProvider());
  }

  private RefundNotifyBodyHelper(String key, String result) {
    if (StringUtils.isBlank(key)) {
      throw new IllegalArgumentException("key is required");
    }
    if (StringUtils.isBlank(result)) {
      throw new IllegalArgumentException("result is required");
    }
    this.key = key;
    try {
      // 初始化结果文档
      this.resultDocument = new SAXReader().read(new StringReader(result));
      // 初始化解密部分的文档
      this.decryptedDocument = new SAXReader().read(new StringReader(
              // 解密指定的加密字段
              decrypt(((Element) this.resultDocument.selectSingleNode(RESULT_ENCRYPT_CONTENT_SELECT)).getTextTrim())));
    } catch (DocumentException e) {
      throw new IllegalStateException("Could not create refund result helper with result: " + result, e);
    }
  }

  /**
   * 创建退款通知结果帮助类
   *
   * @param result 退款通知结果字符串内容
   * @param key 秘钥
   * @return 退款结果帮助类
   */
  public static RefundNotifyBodyHelper create(String result, String key) {
    return new RefundNotifyBodyHelper(key, result);
  }

  /**
   * 结果是否为成功的
   *
   * @return 是否为成功的
   */
  public boolean isReturnSucceeded() {
    if (returnCode == null) {
      Node node = resultDocument.selectSingleNode(RETURN_CODE_CONTENT_SELECT);
      if (node == null) {
        throw new IllegalStateException("No return code field found");
      }
      returnCode = node.getText();
    }
    return returnCode.equals(RETURN_CODE_SUCCESS);
  }

  /**
   * 结果是否为失败的
   *
   * @return 是否为失败的
   */
  public boolean isReturnFailed() {
    if (returnCode == null) {
      Node node = resultDocument.selectSingleNode(RETURN_CODE_CONTENT_SELECT);
      if (node == null) {
        throw new IllegalStateException("No return code field found");
      }
      returnCode = node.getText();
    }
    return returnCode.equals(RETURN_CODE_FAIL);
  }

  /**
   * 返回结果信息
   *
   * @return 结果信息
   */
  public String getReturnMessage() {
    if (returnMessage == null) {
      Node node = resultDocument.selectSingleNode(RETURN_MSG_CONTENT_SELECT);
      if (node == null) {
        throw new IllegalStateException("No return message field found");
      }
      returnMessage = node.getText();
    }
    return returnMessage;
  }

  /**
   * 转换为请求结果对象
   *
   * @param responseType 请求结果对象类型
   * @return 请求结果对象
   */
  public <R> R parseToObject(Class<R> responseType) {
    return ResponseBodyXmlUtils.parseToObject(decryptedDocument, responseType);
  }

  /**
   * 解密
   *
   * @param encrypt 加密的内容
   * @return 解密后的内容
   */
  private String decrypt(String encrypt) {
    String keyMd5 = DigestUtils.md5Hex(key).toLowerCase();
    SecretKeySpec keySpec = new SecretKeySpec(keyMd5.getBytes(), ALGORITHM);
    try {
      Cipher cipher = Cipher.getInstance(ALGORITHM_MODE_PADDING);
      cipher.init(Cipher.DECRYPT_MODE, keySpec);
      return new String(cipher.doFinal(Base64.getDecoder().decode(encrypt)));
    } catch (NoSuchAlgorithmException
            | NoSuchPaddingException
            | InvalidKeyException
            | IllegalBlockSizeException
            | BadPaddingException e) {
      throw new IllegalStateException("Could not decrypt: " + encrypt, e);
    }
  }
}
