package me.gaigeshen.wechat.pay;

import org.apache.commons.codec.digest.DigestUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.util.Base64;

/**
 * 退款结果帮助类
 *
 * @author gaigeshen
 */
public class RefundResultHelper {
  private static final String ALGORITHM = "AES";
  private static final String ALGORITHM_MODE_PADDING = "AES/ECB/PKCS7Padding";

  private final String encrypted;
  private final String key;

  static {
    // Support AES/ECB/PKCS7Padding
    Security.addProvider(new BouncyCastleProvider());
  }

  private RefundResultHelper(String encrypted, String key) {
    this.encrypted = encrypted;
    this.key = key;
  }

  public static RefundResultHelper create(String encrypted, String key) {
    return new RefundResultHelper(encrypted, key);
  }
  
  public String decrypt() {
    String keyMd5 = DigestUtils.md5Hex(key).toLowerCase();
    SecretKeySpec keySpec = new SecretKeySpec(keyMd5.getBytes(), ALGORITHM);
    try {
      Cipher cipher = Cipher.getInstance(ALGORITHM_MODE_PADDING);
      cipher.init(Cipher.DECRYPT_MODE, keySpec);
      return new String(cipher.doFinal(Base64.getDecoder().decode(encrypted)));
    } catch (NoSuchAlgorithmException
            | NoSuchPaddingException
            | InvalidKeyException
            | IllegalBlockSizeException
            | BadPaddingException e) {
      throw new IllegalStateException("Could not decrypt refund result: " + encrypted, e);
    }
  }
}
