package me.gaigeshen.wechat.pay;

import java.util.function.Consumer;

/**
 * 下载文件请求结果处理器
 *
 * @author gaigeshen
 */
public class DownloadResponseBodyHandler<R extends Response> implements ResponseBodyHandler {
  private final Class<R> failedResultType;
  private final Consumer<byte[]> contentConsumer;
  private R failedResult;

  /**
   * 创建下载文件请求结果处理器
   *
   * @param failedResultType 下载失败的时候的结果类型
   * @param contentConsumer 下载成功时，文件内容的消费者
   */
  public DownloadResponseBodyHandler(Class<R> failedResultType, Consumer<byte[]> contentConsumer) {
    if (failedResultType == null) {
      throw new IllegalArgumentException("failedResultType is required");
    }
    if (contentConsumer == null) {
      throw new IllegalArgumentException("contentConsumer is required");
    }
    this.failedResultType = failedResultType;
    this.contentConsumer = contentConsumer;
  }

  @Override
  public void handle(String contentType, byte[] content) {
    if (contentType.startsWith("text")) { // 失败的情况，内容类型非文件类型
      failedResult = ResponseBodyHelper.create(new String(content)).parseToObject(failedResultType);
    } else {
      contentConsumer.accept(content);
    }
  }

  /**
   * 返回下载是否是失败的结果
   *
   * @return 下载是否失败
   */
  public boolean isFailed() {
    return failedResult != null;
  }

  /**
   * 只在失败的情况下才有结果
   *
   * @return 失败的结果
   */
  public R getFailedResult() {
    return failedResult;
  }
}
