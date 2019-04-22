package me.gaigeshen.wechat.pay;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import java.io.StringReader;

/**
 * 通知结果帮助类
 *
 * @author gaigeshen
 */
public class NotifyBodyHelper {
  /**
   * 由通知结果响应体转换后的文档
   */
  private final Document document;

  /**
   * 创建通知结果帮助类
   *
   * @param coontent 响应体内容
   * @throws DocumentException 转换响应体为文档的时候失败
   */
  private NotifyBodyHelper(String coontent) throws DocumentException {
    this.document = new SAXReader().read(new StringReader(coontent));
  }

  /**
   * 创建通知结果帮助类
   *
   * @param bodyContent 响应体内容
   * @return 响应体帮助类
   */
  public static NotifyBodyHelper create(String bodyContent) {
    try {
      return new NotifyBodyHelper(bodyContent);
    } catch (DocumentException e) {
      throw new IllegalStateException("Could not create notify body helper", e);
    }
  }

  /**
   * 转换为通知结果对象
   *
   * @param responseType 通知结果对象类型
   * @return 通知结果对象
   */
  public <R> R parseToObject(Class<R> responseType) {
    return ResponseBodyXmlUtils.parseToObject(document, responseType);
  }
}
