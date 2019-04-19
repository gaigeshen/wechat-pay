package me.gaigeshen.wechat.pay.commons;

import java.util.HashMap;
import java.util.Map;

/**
 * 映射构建器
 * 
 * @author gaigeshen
 */
public final class MapBuilder {
  
  private Map<String, Object> map;

  /**
   * 创建映射构建器
   *
   * @param capacity 容量
   */
  private MapBuilder(int capacity) {
    this.map = new HashMap<>(capacity);
  }
  
  /**
   * 创建映射构建器
   * 
   * @param capacity 容量
   * @return 映射构建器
   */
  public static MapBuilder builder(int capacity) {
    return new MapBuilder(capacity);
  }
  
  /**
   * 返回映射构建器
   *
   * @return 映射构建器
   */
  public static MapBuilder builder() {
    return builder(16);
  }

  /**
   * 添加键值对
   *
   * @param key 键
   * @param value 值
   * @return 当前的映射构建器
   */
  public MapBuilder put(String key, Object value) {
    this.map.put(key, value);
    return this;
  }

  /**
   * 添加键值对，数据来自另外一个映射
   *
   * @param map 映射
   * @return 当前的映射构建器
   */
  public MapBuilder putAll(Map<String, Object> map) {
    this.map.putAll(map);
    return this;
  }

  /**
   * 构建映射
   *
   * @return 映射
   */
  public Map<String, Object> build() {
    return this.map;
  }

}
