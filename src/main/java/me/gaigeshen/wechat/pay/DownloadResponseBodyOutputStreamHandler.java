package me.gaigeshen.wechat.pay;

import java.io.IOException;
import java.io.OutputStream;

/**
 * 下载文件请求结果处理器，可指定输出流
 *
 * @author gaigeshen
 */
public class DownloadResponseBodyOutputStreamHandler<R extends Response> extends DownloadResponseBodyHandler<R> {
  /**
   * 创建下载文件请求结果处理器
   *
   * @param failedResultType 下载失败的时候的结果类型
   * @param out 输出流
   */
  public DownloadResponseBodyOutputStreamHandler(Class<R> failedResultType, OutputStream out) {
    super(failedResultType, bytes -> {
      try {
        out.write(bytes);
      } catch (IOException e) {
        throw new IllegalStateException("Could not write to stream", e);
      }
    });
  }
}
