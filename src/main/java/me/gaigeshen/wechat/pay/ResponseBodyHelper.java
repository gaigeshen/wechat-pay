package me.gaigeshen.wechat.pay;

import me.gaigeshen.wechat.pay.commons.NameUtils;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.StringReader;
import java.lang.reflect.Field;

/**
 * 响应体帮助类
 *
 * @author gaigeshen
 */
final class ResponseBodyHelper {
  /**
   * 响应体内容，是字符串
   */
  private final String coontent;
  /**
   * 由响应体转换后的文档
   */
  private final Document document;

  /**
   * 创建响应体帮助类
   *
   * @param coontent 响应体内容
   * @throws DocumentException 转换响应体为文档的时候失败
   */
  private ResponseBodyHelper(String coontent) throws DocumentException {
    this.coontent = coontent;
    this.document = new SAXReader().read(new StringReader(this.coontent));
  }

  /**
   * 创建响应体帮助类
   *
   * @param bodyContent 响应体内容
   * @return 响应体帮助类
   */
  static ResponseBodyHelper create(String bodyContent) {
    try {
      return new ResponseBodyHelper(bodyContent);
    } catch (DocumentException e) {
      throw new IllegalStateException("Could not create response body helper", e);
    }
  }

  /**
   * 转换为请求结果对象
   *
   * @param responseType 请求结果对象类型
   * @return 请求结果对象
   */
  <R> R parseToObject(Class<R> responseType) {
    R response;
    try {
      response = responseType.newInstance();
    } catch (InstantiationException | IllegalAccessException e) {
      throw new IllegalStateException(
              "Could not create new response object: " + responseType, e);
    }
    Field[] fields = FieldUtils.getAllFields(responseType);
    for (Field field : fields) {
      field.setAccessible(true);
      Element node = (Element) document.selectSingleNode("//" + NameUtils.camelToUnderline(field.getName()));
      if (node == null) {
        continue; // 可能不存在该字段
      }
      String value = node.getTextTrim();
      Class<?> fieldType = field.getType();
      try {
        // 目前只考虑三种类型的字段
        if (fieldType == String.class) {
          field.set(response, value);
        }
        else if (ClassUtils.isAssignable(int.class, fieldType)) {
          field.set(response, Integer.valueOf(value));
        }
        else if (ClassUtils.isAssignable(long.class, fieldType)) {
          field.set(response, Long.valueOf(value));
        }
      } catch (IllegalAccessException e) {
        throw new IllegalStateException("Could not assign value to response field: " + field, e);
      }
    }
    return response;
  }

}
